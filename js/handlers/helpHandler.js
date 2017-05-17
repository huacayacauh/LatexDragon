/**
 * Module handling the 'HELP' tab.
 * @module helpHandler
 */
var self = module.exports = {
	/**
	 * Initialize everything the tab need in order to run properly.
	 */
	init: () => {
		const utils = require('../utils')
		utils.typesetMath()

		self.setAnimations()
	},

	/**
	 * Set the animations, displayed when the tab is loaded.
	 */
	setAnimations: () => {
		$('.main').animateCss('slideInDown', 0.3)
	}
}
