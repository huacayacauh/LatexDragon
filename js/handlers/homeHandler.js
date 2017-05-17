/**
 * Module handling the 'HOME' tab.
 * @module homeHandler
 */
var self = module.exports = {
	/**
	 * Initialize everything the tab need in order to run properly.
	 */
	init: () => {
		self.setAnimations()

		//In the case we just started the application
		if ($('#window-content').is(':hidden')) {
			$('#window-content').show()
			$('#loading-screen').hide()
		}
	},

	/**
	 * Set the animations, displayed when the tab is loaded.
	 */
	setAnimations: () => {
			$('.main').animateCss('slideInDown', 0.3)
	}
}
