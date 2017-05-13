/**
 * @module helpHandler
 */
var self = module.exports = {
	init: () => {
		const utils = require('../utils')
		utils.typesetMath()

		self.setAnimations()
	},

	setAnimations: () => {
		$('.main').animateCss('slideInDown', 0.3)
	}
}
