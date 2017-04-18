/**
 * module containing all the function for handling the elements/event of the game tab
 * @module gameHandler
 */
var self = module.exports = {
	/**
	 * Initialize everything the tab need in order to run properly.
	 */
	init: () => {
		const utils = require('../utils')

		self.setAnimations()

		utils.typesetMath()
	},

  /**
   * Set the events for the game tab.
	 * Only the mouse clicks events are working right now.
   */
  setEvents: () => {
		const mouseClickHandler = require('./mouseClickHandler')

    mouseClickHandler.setEvents();
    //DragNDropHandler.setEvents(obj);
  },

	setAnimations: () => {
		$('.toolbar').show().animateCss('slideInLeft', 0.3, 0, () => {
			$('#main-content').show().animateCss('slideInLeft', 0.3)
			$('#timeline').show().animateCss('slideInUp', 0.3)
		})

	},

  /**
   * Request used to start a new game, will ask the server to start a new game
	 * using the parameters contained in gameState to define the configuration of the
	 * game.
   */
  startNewGame: () => {
		const instance = require('../Application')
		const Request = require('../Request')

    var request = Request.buildRequest("START", self.startNewGameResponse);

    request.send('/' + instance.gameState.mode + '/' + instance.gameState.ruleSet + '/' + instance.gameState.formulaId + '/' + instance.gameState.useTheorem);
  },

  /**
   * Response of the startNewGame request.
   * Will display an error message if the server can't create a new game.
   * If it's a success it will register the game ID and send a request to get the
   * game state.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status response status from the request
   */
  startNewGameResponse: (response, status) => {
		const instance = require('../Application')
		const Request = require('../Request')

    //Request error
    if (status != "success")
      instance.displayErrorNotification("#gameNotification", "Erreur lors de la requête, status : " + status + " (" + response.status + ").");

    var obj = JSON.parse(response.responseText);

    //Server error
    if (obj.status == "FAILURE")
      instance.displayErrorNotification("#gameNotification", obj.complementaryInfo);

    instance.gameState.gameId = obj.id;

    var request = Request.buildRequest("GAMESTATE", self.gameStartResponse);
    request.send("/" + obj.id);
  },

  /**
   * Response of the request to get the game state after a new game is created.
   * Will start a timer if it's enabled and delegate the processing of the response
   * to gameUpdateMathResponse.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status response status from the request
   */
  gameStartResponse: (response, status) => {
		const instance = require('../Application')
		const Countdown = require ('../Countdown')

    self.gameUpdateMathResponse(response, status);

    //Stop timer
    if (instance.countdown != null) {
      instance.countdown.stopCountdown();
      $("#gameTimer").html("");
    }

    //Start timer
		if (instance.gameState.mode == 'NORMAL') {
			instance.countdown = new Countdown (Countdown.minutesToMilliseconds(1), self.timerOnOver, self.timerOnUpdate);
			instance.countdown.startCountdown();
		}
  },

  /**
   * Send a request to get the game state.
   */
	gameStateRequest: () => {
		const instance = require('../Application')
		const Request = require('../Request')
		const Countdown = require ('../Countdown')

		var request = Request.buildRequest('GAMESTATE', self.gameUpdateMathResponse)

		request.send('/' + instance.gameState.gameId)

		//Stop timer
    if (instance.countdown != null) {
      instance.countdown.stopCountdown();
      $("#gameTimer").html("");
    }

    //Start timer
		if (instance.gameState.mode == 'NORMAL') {
			instance.countdown = new Countdown (Countdown.minutesToMilliseconds(1), self.timerOnOver, self.timerOnUpdate);
			instance.countdown.startCountdown();
		}
  },

  /**
   * Function that process the response from the server containing the formula state.
   * Will throw an error if the request failed.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status response status from the request
   * @throws if the request fail and the function receive a status diffrent than success.
   */
  gameUpdateMathResponse: (response, status) => {
		const instance = require('../Application')
		const utils = require('../utils')

    if (status == "success")
      instance.gameState.currentState = JSON.parse(response.responseText);
    else {
      instance.displayErrorNotification("#gameNotification", "Erreur lors de la requête, status : " + status + " (" + response.status + ").");
      throw "[ERROR]: request response invalid, request might have failed.";
    }

    //Set new math
    $("#main-formule").text(instance.gameState.currentState.math).hide("fast");

		//Update Timeline
		self.updateTimeline(instance.gameState.currentState.timeline)

    //Call mathJax typeset and show the formule once it's done
    utils.typesetMath(() => {
      $("#main-formule").show("fast")
      //Set events
      self.setEvents()
			instance.settings.applySettings()
    });
  },

  /**
   * Send a request to the server to apply a rule to the formula.
   * Call gameUpdateMathResponse to process the response.
   * @param {Event} event jQuery Event object
   */
  gameRuleRequest: (event) => {
		const instance = require('../Application')
		const Request = require('../Request')

    event.stopPropagation();

    var request = Request.buildRequest("APPLYRULE", self.gameUpdateMathResponse);
    request.send("/" + instance.gameState.gameId + "/" + event.data.value.expId + "/" + event.data.value.ruleId + "/" + event.data.value.context);

    if ($("#tooltip").is(":visible"))
      $("#tooltip").hide(100);
  },

  /**
   * Function handling the end of the countdown.
   * When the countdown is over an OVER request is send to the server
   * to signal the game is over.
   * When the request is over call gameOverResponse to handle the end of the game
   * clientside.
   */
  timerOnOver: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		//Clear timer text
    $("#gameTimer").html("");

    instance.displaySuccessNotification("#gameNotification", "Temps écouler, partie finie.");

		//Delete timer
    instance.countdown = null;

    Request.buildRequest("OVER", self.gameOverResponse).send("/" + instance.gameState.gameId);
  },

  /**
   * Function handling the end of the game clientside, restart the window by hiding
   * the formula and the tools and then show the start button.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status response status from the request
   */
  gameOverResponse: (response, status) => {
		const instance = require('../Application')

    if (status != "success") {
      instance.displayErrorNotification("#gameNotification", "Erreur lors de la requête, status : " + status + " (" + response.status + ").");
      throw "[ERROR]: request response invalid, request might have failed.";
    }

    $("#tools").hide("slow");

    $("#main-formule").hide("slow");

    $(".jumbotron:hidden").show("slow");
  },

  /**
   * Function handling the update of the countdown.
   * Each time the countdown is updated the timer text element in the window is updated too.
   * @param {Countdown} countdown countdown object
   */
  timerOnUpdate: (countdown) => {
    $("#gameTimer").html($("<h1></h1>").text(countdown.toString()));
  },

  /**
   * Function handling the restart button.
   * Send an OVER request then send a START request using the default handler for
   * those request.
   */
  restartGame: () => {
		const instance = require('../Application')
		const Request = require('../Request')

    Request.buildRequest("OVER", self.startNewGame(instance.gameState.formulaId)).send("/" + instance.gameState.gameId);
  },

	previousState: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		Request.buildRequest('PREVIOUS', self.gameUpdateMathResponse).send('/' + instance.gameState.gameId)
	},

	nextState: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		Request.buildRequest('NEXT', self.gameUpdateMathResponse).send('/' + instance.gameState.gameId)
	},

	toggleTimeline: () => {
		if ($('#hideTimeline').is(':visible')) {
			$('#hideTimeline').hide()
			$('#showTimeline').show().css('display', 'flex')

			$('#timeline').animateCss('slideOutDown', 0.2, 0, () => {
				$('#timeline-elements').hide()
			})
		}
		else {
			$('#hideTimeline').show().css('display', 'flex')
			$('#showTimeline').hide()

			$('#timeline-elements').show()
			$('#timeline').animateCss('slideInUp', 0.2, 0)
		}
	},

	updateTimeline: (timeline) => {
		$('#timeline-elements').html('')

		for (var i = timeline.elements.length-1 ; i >= 0 ; i--) {
			var elem
			if (i == timeline.current)
				elem = $('<div></div>').addClass('timeline-element current-element btn btn-danger').text(timeline.elements[i].text)
			else
				elem = $('<div></div>').addClass('timeline-element btn btn-info').text(timeline.elements[i].text)

			elem.click({ param: i }, self.timelineOnClickHandler)

			$('#timeline-elements').append(elem)
		}
	},

	timelineOnClickHandler: (event) => {
		if ($('#addTheorem').is(':visible'))
			self.requestStateFromTimeline(event.data.param)
		else
			self.selectForTheorem(event.data.param, event.currentTarget)
	},

	requestStateFromTimeline: (index) => {
		const instance = require('../Application')
		const Request = require('../Request')

		Request.buildRequest('TIMELINE', self.gameUpdateMathResponse).send('/' + instance.gameState.gameId + '/' + index)
	},

	theoremSelection: { start: null, end: null},

	toggleCreateTheorem: () => {
		const instance = require('../Application')

		if ($('#addTheorem').is(':visible')) {
			$('#addTheorem').hide()

			$('#validTheorem').show().css('display', 'flex')
			$('#cancelTheorem').show().css('display', 'flex')
		}
		else {
			$('#addTheorem').show().css('display', 'flex')

			$('#validTheorem').hide()
			$('#cancelTheorem').hide()

			$('.timeline-element').each(function () {
				$(this).removeClass('btn-warning')
				if ($(this).hasClass('current-element'))
					$(this).addClass('btn-danger')
				else
					$(this).addClass('btn-info')
			})
		}

		self.theoremSelection.start = null
		self.theoremSelection.end = null
	},

	selectForTheorem: (index, target) => {
		const instance = require('../Application')

		//If we click on an already selectionned item we unselect it
		if (self.theoremSelection.start == index) {
			self.theoremSelection.start = null
			$(target).removeClass('btn-warning')
			if ($(target).hasClass('current-element'))
				$(target).addClass('btn-danger')
			else
				$(target).addClass('btn-info')
		}
		else if (self.theoremSelection.end == index) {
			self.theoremSelection.end = null
			$(target).removeClass('btn-warning')
			if ($(target).hasClass('current-element'))
				$(target).addClass('btn-danger')
			else
				$(target).addClass('btn-info')
		}
		//Else we first select the starting point then the end one
		else if (self.theoremSelection.start == null) {
			self.theoremSelection.start = index
			$(target).addClass('btn-warning')
			if ($(target).hasClass('current-element'))
				$(target).removeClass('btn-danger')
			else
				$(target).removeClass('btn-info')
		}
		else if (self.theoremSelection.end == null) {
			self.theoremSelection.end = index
			$(target).addClass('btn-warning')
			if ($(target).hasClass('current-element'))
				$(target).removeClass('btn-danger')
			else
				$(target).removeClass('btn-info')
		}
	},

	validTheorem: () => {
		const instance = require('../Application')

		if ((self.theoremSelection.start == null) || (self.theoremSelection.end == null))
			instance.displayPopup('Création d\'un théorème', 'L\' une des 2 valeurs n\'est pas selectionné.', 'OK', 'Annuler', () => { $('#popup').modal('hide') }, () => { $('#popup').modal('hide') })
		else if (self.theoremSelection.start > self.theoremSelection.end)
			instance.displayPopup('Création d\'un théorème', 'Le début du théorème ne peut pas être après la fin.', 'OK', 'Annuler', () => { $('#popup').modal('hide') }, () => { $('#popup').modal('hide') })
		else
			instance.displayPopup('Création d\'un théorème', 'Voulez-vous créer ce théorème ?', 'Oui', 'Annuler', self.sendTheoremCreation, () => { $('#popup').modal('hide') })
	},

	sendTheoremCreation: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		Request.buildRequest('CREATETHEOREM').send('/' + instance.gameState.gameId + '/' + self.theoremSelection.start + '/' + self.theoremSelection.end)

		self.toggleCreateTheorem()
	},

	toggleRulesList: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		if ($('#rules-list').is(':hidden')) {
			Request.buildRequest('RULESLIST', self.rulesListReply).send('/' + instance.gameState.gameId)

			$('#rules-list').animateCss('slideInDown', 0.3)
			$('#rules-list').show()
			$('#rules-loader').show()
		}
		else {
			$('#rules-list').animateCss('slideOutUp', 0.3, 0, () => {
				$('#rules-list').hide()
				$('#rules-content').hide()
			})
		}

	},

	rulesListReply: (response, status) => {
		const instance = require('../Application')

    if (status != "success") {
      instance.displayErrorNotification("#gameNotification", "Erreur lors de la requête, status : " + status + " (" + response.status + ").");
      throw "[ERROR]: request response invalid, request might have failed.";
    }

		self.displayRulesList(JSON.parse(response.responseText).rules)
	},

	displayRulesList: (rules) => {
		$('#rules-content').html('')

		for (var item in rules) {
			for (var type in rules[item]) {
				var title = $('<h1></h1>').addClass('display-3').text(type)
				$('#rules-content').append(title)
				for (var rule in rules[item][type]) {
					var elem = $('<div></div>').addClass('notif alert alert-info').text(rules[item][type][rule])
					$('#rules-content').append(elem)
				}
			}
		}

		$('#rules-loader').hide()
		$('#rules-content').show()
	}
}
