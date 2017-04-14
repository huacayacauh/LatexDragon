/**
 * @module homeHandler
 */
var self = module.exports = {
	init: () => {
		self.setGameState()
		self.setAnimations()

		//In the case we just started the application
		if ($('#window-content').is(':hidden')) {
			$('#window-content').show()
			$('#loading-screen').hide()
		}
	},

	setAnimations: () => {
			$('.main').animateCss('slideInDown', 0.5)
	},

	setGameState: () => {
		const instance = require('../Application')

		if ((instance.gameState.gameId == undefined) || (instance.gameState.gameId == null))
			$('#last-game-container').html('<h3>Aucune partie trouvé.</h3>')
		else {
			$('#last-game-container').html('<h3>Partie trouvé !</h3><code>/config/gamestate.json</code><br/><b>ID:</b> ' + instance.gameState.gameId + '<br/>')
			self.canResumeGame()
		}
	},

	canResumeGame: () => {
		const instance = require('../Application')
		const Request = require('../Request')

		var request = Request.buildRequest('RESUME', self.canResumeGameReply)

		request.send('/' + instance.gameState.gameId)
	},

	canResumeGameReply: (reply, status) => {
		const instance = require('../Application')
		var obj

		if (status == "success")
      obj = JSON.parse(reply.responseText);
    else {
      instance.displayErrorNotification("#homeNotification", "Erreur lors de la requête, statut : " + status + " (" + reply.status + ").");
      throw "[ERROR]: request response invalid, request might have failed.";
    }

		$('#last-game-container').append('<b>Réponse du serveur :</b> ' + obj.complementaryInfo)

		if (obj.status == 'SUCCESS') {
			$('#last-game-container').append('<br/><button id="resume-button" class="btn">Continuer <i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>')
			$('#resume-button').click(self.resumeGame)
		}
		else {
			$('#last-game-container').append('<br/><button id="play-button" class="btn">Jouer <i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>')
			$('#play-button').click(() => {
				require('../Application').requestHtml('GAMEMODE')
			})
		}
	},

	resumeGame: () => {
		const instance = require('../Application')
		const gameHandler = require('./gameHandler')

		instance.requestHtml('GAME')

		gameHandler.gameStateRequest()
	}
}
