/**
 * Module handling the 'GAMEMODE' tab.
 * @module gameModeHandler
 */
var self = module.exports = {
	/**
	 * Initialize everything the tab need in order to run properly.
	 */
	init: () => {
		self.setAnimations()
	},

	/**
	 * Set the animations, displayed when the tab is loaded.
	 */
	setAnimations: () => {
			$('.j1').animateCss('slideInDown', 0.5)
			$('.j2').animateCss('slideInDown', 0.5, 0.1)
	},

	/**
	 * Function handling the mode selection.
	 * Will start the creation of a new GameState, store the game mode and load
	 * the 'GAMERULESET' tab.
	 * @param {String} mode game mode to be used for the new game.
	 */
	selectMode: (mode) => {
		const instance = require('../Application')
		const GameState = require('../GameState')

		instance.gameState.startCreation()
		instance.gameState.inCreation.mode = mode

		instance.requestHtml('GAMERULESET')
	}
}
