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
		var obj = JSON.parse(reply.responseText)
		$('#last-game-container').append('Réponse du serveur : <b>' + obj.complementaryInfo + '</b>')
		if (obj.status == 'SUCCESS') {
			$('#last-game-container').append('<button id="resume-button" class="btn">Continuer <i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>')
			$('#resume-button').click(self.resumeGame)
		}
		else {
			$('#last-game-container').append('<button id="play-button" class="btn">Jouer <i class="fa fa-long-arrow-right" aria-hidden="true"></i></button>')
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
