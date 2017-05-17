/**
 * Module containing all the function for handling the elements/event of the settings tabe.
 * @module settingsHandler
 */
var self = module.exports = {
	/**
	 * Initialize everything the tab need in order to run properly.
	 */
	init: () => {
		self.setEvents()
		self.setValues()
		self.setAnimations()
	},

	/**
	 * Set the events of the elements in the settings tab.
	 */
	setEvents: () => {
		//Color
		$('#settings-color').change(self.colorHandler)
		//mathJax font size
		$('.settings-mathSize').click(self.mathSizeHandler)
		//Auto close notification on/off
		$('#settings-notifOnOff').change(self.autoCloseNotifHandler)
		//highlighting on/off
		$('#settings-highlightingOnOff').change(self.highlightingHandler)
		//highlight color
		$('#settings-highlightColor').change(self.highlightColorHandler)
		//tooltip display
		$('#settings-tooltipOnOff').change(self.displayTooltipHandler)
	},

	/**
	 * Set the animations, displayed when the tab is loaded.
	 */
	setAnimations: () => {
		$('.main').animateCss('slideInDown', 0.3)
	},

	/**
	 * Set the default values of the element in the tab to match their value
	 * in the application settrings
	 */
	setValues: () => {
		const instance = require('../Application')

		$('#settings-color').val(instance.settings.color)
		$('#settings-timer').attr('checked', instance.settings.timer)
		$('#settings-notifOnOff').attr('checked', instance.settings.autoCloseNotif)
		$('#settings-highlightingOnOff').attr('checked', instance.settings.highlighting)
		$('#settings-highlightColor').val(instance.settings.highlightColor)
		$('#settings-tooltipOnOff').attr('checked', instance.settings.displayTooltip)

		self.memoryUsageHandler()
		self.processInfoHandler()
	},

	/**
	 * Handler for the color select.
	 */
	colorHandler: function () {
		const instance = require ('../Application')
		instance.settings.setColor($(this).val())
	},

	/**
	 * Handler for the font size select.
	 */
	mathSizeHandler: function () {
		const instance = require ('../Application')
		instance.settings.setMathSize($(this).text())
	},

	/**
	 * Handler for the highlighting on/off select.
	 */
	highlightingHandler: function () {
		const instance = require ('../Application')
		instance.settings.setHighlighting($(this).is(':checked'))
	},

	/**
	 * Handler for the highlight color select.
	 */
	highlightColorHandler: function () {
		const instance = require ('../Application')
		instance.settings.setHighlightColor($(this).val())
	},

	/**
	 * Handler for the notif close on/off select.
	 */
	autoCloseNotifHandler: function () {
		const instance = require ('../Application')
		instance.settings.setAutoCloseNotif($(this).is(':checked'))
	},

	/**
	 * Handler for the tooltip on/off select.
	 */
	displayTooltipHandler: function () {
		const instance = require ('../Application')
		instance.settings.setDisplayTooltip($(this).is(':checked'))
	},

	/**
	 * Handler for the memory usage.
	 * Memory usage is updated once every 1000 milliseconds.
	 */
	memoryUsageHandler: () => {
		const instance = require ('../Application')

		if (instance.currentTab == 'SETTINGS') {
			var usage = instance.getMemoryUsage()

			$('#settings-memoryUsage').html('')
			$('#settings-memoryUsage').append(
				$('<div></div>').text('Actuellement utilisé : ').append($('<b></b>').text(usage.workingSetSize + ' Ko'))
			).append(
				$('<div></div>').text('Maximum : ').append($('<b></b>').text(usage.peakWorkingSetSize + ' Ko'))
			).append(
				$('<div></div>').text('Mémoire caché utilisé : ').append($('<b></b>').text(usage.privateBytes + ' Ko'))
			).append(
				$('<div></div>').text('Mémoire partager utilisé : ').append($('<b></b>').text(usage.sharedBytes + ' Ko'))
			)

			setTimeout(self.memoryUsageHandler, 1000)
		}
	},

	/**
	 * Handler for the process informations.
	 */
	processInfoHandler: () => {
		const instance = require ('../Application')
		var process = instance.getProcess()
		const app = require('electron').remote.app

		$('#settings-processInfo').append(
			$('<div></div>').text('LibreDragon Client version : ').append($('<b></b>').text(app.getVersion()))
		).append(
			$('<div></div>').text('Process type : ').append($('<b></b>').text(process.type))
		).append(
			$('<div></div>').text('Electron version : ').append($('<b></b>').text(process.versions.electron))
		).append(
			$('<div></div>').text('Chrome version : ').append($('<b></b>').text(process.versions.chrome))
		).append(
			$('<div></div>').text('Node.js version : ').append($('<b></b>').text(process.versions.node))
		)
	}
}
