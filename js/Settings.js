/** Class handling the settings of the application */
class Settings {

	/**
	* @param {String} color color of the background (grandient), default is
	* @param {String} mathSize font size of the mathJax contained in the div #main-formule
	* @param {bool} autoCloseNotif boolean used to determine if the app notifications are closed automatically or not
	* @param {int} notifTimer value (in milliseconds) used for the notif lifespan if autoCloseNotif is true
	* @param {bool} highlighting do we highlight the formula ?
	* @param {String} highlightColor color of the highlight
	* @param {bool} displayTooltip do we display the tooltip when the user mouse over the formula ?
	* @param {bool} devmode is the app using devmode ?
	*/
	constructor (color = 'linear-gradient(to bottom, #1AD6FD, #1D62F0)', mathSize = '50px', autoCloseNotif = true, notifTimer = 2000, highlighting = true, highlightColor = '#eee', displayTooltip = true, devmode = false) {
		this.color = color
		this.mathSize = mathSize
		this.autoCloseNotif = autoCloseNotif
		this.notifTimer = notifTimer
		this.highlighting = highlighting
		this.highlightColor = highlightColor
		this.displayTooltip = displayTooltip
		this.devmode = devmode
	}

	/**
	* Create a new Settings object. If a settings file is present return a Settings
	* object using the values contained in the settings file.
	* @return {Settings}
	* @static
	*/
	static initSettings () {
		const utils = require('./utils')

		var data = utils.readConfigSync('settings.json')

		var settings = new Settings()

		if (data == null) {
			console.log('[CLIENT]: No settings file found, using default settings.')
			return settings
		}

		data = JSON.parse(data)

		return Object.assign(settings, data)
	}

	/**
	* Apply the settings to the application.
	* Not all settings have to be applied.
	*/
	applySettings () {
		const instance = require('./Application')

		//Apply background color
		$('html').css('background-image', this.color)
		//Apply mathJax font size
		$('#main-formule').css('font-size', this.mathSize)

		console.log('[CLIENT]: Settings applied')
	}

	/**
	* Set the color attribute, apply the changes and display a message.
	* @param {String} value value of the new color
	*/
	setColor (value) {
		this.color = value
		this.applySettings()

		const instance = require ('./Application')
		instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.')
	}

	/**
	* Set the mathSize attribute, apply the changes and display a message.
	* @param {String} value value of the font size
	*/
	setMathSize (value) {
		this.mathSize = value
		this.applySettings()

		const instance = require ('./Application')
		instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.')
	}

	/**
	* Set the autoCloseNotif attribute and display a message.
	* @param {bool} value
	*/
	setAutoCloseNotif (value) {
		this.autoCloseNotif = value

		const instance = require ('./Application')
		instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.')
	}

	/**
	* Set the highlighting attribute and display a message.
	* @param {bool} value
	*/
	setHighlighting (value) {
		this.highlighting = value

		const instance = require ('./Application')
		instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.')
	}

	/**
	* Set the highlight color attribute and display a message.
	* @param {String} value value of the new highlight color
	*/
	setHighlightColor (value) {
		this.highlightColor = value

		const instance = require ('./Application')
		instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.')
	}

	/**
	* Set the display tooltip attribute and display a message.
	* @param {bool} value
	*/
	setDisplayTooltip (value) {
		this.displayTooltip = value

		const instance = require ('./Application')
		instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.')
	}

	/**
	* Return a string of this object.
	*/
	toString () {
		return JSON.stringify(this, null, 2)
	}
}

/**
* Settings module.
* Check the Settings class for more informations.
* @module settings
* @see {@link Settings}
*/
module.exports = Settings
