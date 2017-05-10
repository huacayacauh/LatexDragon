/**
 * @module homeHandler
 */
var self = module.exports = {
	init: () => {
		self.setAnimations()

		//In the case we just started the application
		if ($('#window-content').is(':hidden')) {
			$('#window-content').show()
			$('#loading-screen').hide()
		}
	},

	setAnimations: () => {
			$('.main').animateCss('slideInDown', 0.5)
	}
}
