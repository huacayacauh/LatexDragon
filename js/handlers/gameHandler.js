//TODO: sort out the mathId/gameId bullshit in the start request

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
			$('.rules-bar').animateCss('slideInLeft', 0.3, 0, () => {
				$('.toolbar').show().animateCss('slideInLeft', 0.3, 0, () => {
					$('#main-content').show().animateCss('slideInLeft', 0.3)
				})
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

	startTheorem: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		Request.buildRequest("STARTTHEOREM").send('/' + instance.gameState.gameId)
	},

	endTheorem: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		Request.buildRequest("ENDTHEOREM").send('/' + instance.gameState.gameId + '/true')
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
	}
}
