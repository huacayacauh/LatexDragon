/**
 * @module gameRuleSetHandler
 */
var self = module.exports = {
	init: () => {
		self.setEvents()
		self.setAnimations()
	},

	setEvents: () => {
		const utils = require('../utils')

		utils.typesetMath()
	},

	setAnimations: () => {
			$('.j1').animateCss('slideInDown', 0.5)
			$('.j2').animateCss('slideInDown', 0.5, 0.1)
			$('.j3').animateCss('slideInDown', 0.5, 0.2)
	},

	selectRuleSet: (ruleSet) => {
		const instance = require('../Application')
		const gameHandler = require('./gameHandler')

		instance.gameState.ruleSet = ruleSet
		instance.gameState.useTheorem = $('#rule1-theorem').is(':checked')
		instance.gameState.formulaId = $('#rule1-formula').val()

		instance.requestHtml('GAME')

		gameHandler.startNewGame()
	}
}
