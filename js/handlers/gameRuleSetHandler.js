/**
 * Module handling the 'GAMERULESET' tab.
 * @module gameRuleSetHandler
 */
var self = module.exports = {
	/**
	 * Initialize everything the tab need in order to run properly.
	 */
	init: () => {
		const utils = require('../utils')
		utils.typesetMath()

		self.setAnimations()
		self.getFormulaList()
	},

	/**
	 * Set the animations, displayed when the tab is loaded.
	 */
	setAnimations: () => {
			$('.j1').animateCss('slideInDown', 0.5)
			$('.j2').animateCss('slideInDown', 0.5, 0.1)
			$('.j3').animateCss('slideInDown', 0.5, 0.2)
	},

	/**
	 * Function handling the selection of the set of rules, the use of theorems,
	 * and the formula.
	 * Will store everyting in the currently created GameState and call
	 * finishCreation() to finish it's creation, will then load the 'GAME' tab.
	 * @param {int} ruleSet id of the rule set
	 * @param {int} formule id of the formula
	 */
	selectRuleSet: (ruleSet, formula) => {
		const instance = require('../Application')

		instance.gameState.inCreation.ruleSet = ruleSet
		instance.gameState.inCreation.useTheorem = $('#rule1-theorem').is(':checked')
		instance.gameState.inCreation.formulaId = formula
		instance.gameState.inCreation.formulaLatex = self.formulaList[$('#rule1-formula').val()]

		instance.gameState.finishCreation()

		instance.requestHtml('GAME')
	},

	/** Object containing the list of playable formulas */
	formulaList: null,

	/**
	 * Send a 'FORMULALIST' request to get the list of playable formulas.
	 */
	getFormulaList: () => {
		const Request = require('../Request')
		Request.buildRequest('FORMULALIST', self.getFormulaListReply).send()
	},

	/**
	 * Response of the 'FORMULALIST' request.
	 * @param {Object} response response from the request (jQuery ajax response)
	 * @param {String} status response status from the request
	 * @throws will throw an error if the request failed
	 */
	getFormulaListReply: (response, status) => {
		const instance = require('../Application')
		const Request = require('../Request')

		var o = Request.checkError(response, status)

		if (o === false)
			throw '[ERROR]: request response invalid, request might have failed.'

		self.formulaList = o.expression

		self.setFormulaList()
	},

	/**
	 * Function handlling the creation of the list of formulas.
	 */
	setFormulaList: () => {
		const utils = require('../utils')

		$('#rule1-formulas').html('')
		for (var i in self.formulaList) {
			var elem = $('<a></a>').text(self.formulaList[i]).addClass('formula-select')
			elem.on('click', {index: i}, (event) => {
				self.selectRuleSet(1, event.data.index)
			})
			$('#rule1-formulas').append(elem)
			$('#rule1-formulas').append('<hr class="my-4">')
		}

		utils.typesetMath(null, 'rule1-formulas')
	}
}
