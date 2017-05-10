/**
 * @module gameModeHandler
 */
var self = module.exports = {
	init: () => {
		self.setEvents()
		self.setAnimations()
	},

	setEvents: () => {
	},

	setAnimations: () => {
			$('.j1').animateCss('slideInDown', 0.5)
			$('.j2').animateCss('slideInDown', 0.5, 0.1)
	},

	selectMode: (mode) => {
		const instance = require('../Application')
		const GameState = require('../GameState')

		instance.gameState.startCreation()
		instance.gameState.inCreation.mode = mode

		instance.requestHtml('GAMERULESET')
	}
}
