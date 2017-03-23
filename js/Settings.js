/** Class handling the settings of the application */
class Settings {

  /**
   * @param {string} color color of the background (grandient)
   * @param {string} mathSize font size of the mathJax contained in the div #main-formule
   * @param {bool} autoCloseNotif boolean used to determine if the app notifications are closed automatically or not
   * @param {int} notifTimer value (in milliseconds) used for the notif lifespan if autoCloseNotif is true
   */
	constructor (color = 'linear-gradient(to bottom, #1AD6FD, #1D62F0)', mathSize = '50px', autoCloseNotif = true, notifTimer = 2000) {
    this.color = color;
    this.mathSize = mathSize;
    this.autoCloseNotif = autoCloseNotif;
    this.notifTimer = notifTimer;
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

		if (data == null)
			return settings

		data = JSON.parse(data)

		return Object.assign(settings, data)
  }

  /**
   * Apply the settings to the application.
   */
  applySettings () {
		const instance = require('./Application')
    //Apply background color
    $('html').css('background-image', this.color);
    //Apply mathJax font size
		console.log(instance.settings.mathSize + ' !important')
		console.log(this)
		if ($('#main-formule')[0] != undefined)
			$('#main-formule')[0].style.setProperty('font-size', this.mathSize, 'important')
    //$('#main-formule').css('font-size', this.mathSize + ' !important');
    console.log('[CLIENT]: Settings applied')
  }

  /**
   * Set the color attribute, apply the changes and display a message.
   */
  setColor (value) {
    this.color = value;
    this.applySettings();

		const instance = require ('./Application')
    instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.');
  }

  /**
   * Set the mathSize attribute, apply the changes and display a message.
   */
  setMathSize (value) {
    this.mathSize = value;
    this.applySettings();

		const instance = require ('./Application')
    instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.');
  }

  /**
   * Set the autoCloseNotif attribute and display a message.
   */
  setAutoCloseNotif (value) {
    this.autoCloseNotif = value;

		const instance = require ('./Application')
    instance.displaySuccessNotification('#settingsNotification', 'Nouveaux paramètres enregistré.');
  }

	toString () {
		return JSON.stringify(this, null, 2)
	}
}

/**
 * Settings module.
 * @module settings
 * @see {@link Settings}
 */
module.exports = Settings
