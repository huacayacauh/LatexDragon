/**
 * @module gameRuleSetHandler
 */
var self = module.exports = {
	init: () => {
		self.setEvents()
		self.setAnimations()
		self.getFormulaList()
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

		instance.gameState.inCreation.ruleSet = ruleSet
		instance.gameState.inCreation.useTheorem = $('#rule1-theorem').is(':checked')
		instance.gameState.inCreation.formulaId = $('#rule1-formula').val()
		instance.gameState.inCreation.formulaLatex = self.formulaList[$('#rule1-formula').val()]

		instance.gameState.finishCreation()

		instance.requestHtml('GAME')
	},

	formulaList: null,

	getFormulaList: () => {
		const Request = require('../Request')
		Request.buildRequest('FORMULALIST', self.getFormulaListReply).send()
	},

	getFormulaListReply: (response, status) => {
		const instance = require('../Application')
		const Request = require('../Request')

		var o = Request.checkError(response, status)

		if (o === false)
			throw '[ERROR]: request response invalid, request might have failed.'

		self.formulaList = o.expression

		self.setFormulaList()
	},

	setFormulaList: () => {
		const utils = require('../utils')

		$('#rule1-formula').html('')
		for (var i in self.formulaList)
			$('#rule1-formula').append('<option value="' + i + '">' + self.formulaList[i] + '</option>')

		utils.typesetMath(null, 'rule1-formula')
	}
}
