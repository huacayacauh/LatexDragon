/** Class handling the settings of the application */
class Settings {

  /**
   * @param {string} color color of the background (grandient)
   * @param {string} mathSize font size of the mathJax contained in the div #main-formule
   * @param {bool} timer boolean used to determine if we use a timer or not during the game
   * @param {bool} autoCloseNotif boolean used to determine if the app notifications are closed automatically or not
   * @param {int} notifTimer value (in milliseconds) used for the notif lifespan if autoCloseNotif is true
   */
  constructor (color, mathSize, timer, autoCloseNotif, notifTimer) {
    this.color = color;
    this.mathSize = mathSize;
    this.timer = timer;
    this.autoCloseNotif = autoCloseNotif;
    this.notifTimer = notifTimer;
  }

  /**
   * Create a settings object using default value.
   * @return {Settings}
   * @static
   */
  static useDefault () {
    return new Settings ("linear-gradient(to bottom, #1AD6FD, #1D62F0)", "100px", true, true, 1500);
  }

  /**
   * Apply the settings to the application.
   */
  applySettings () {
    //Apply background color
    $("html").css("background-image", this.color);
    //Apply mathJax font size
    $("#main-formule").css("font-size", this.mathSize);
    console.log("[CLIENT]: Settings applied")
  }

  /**
   * Set the color attribute, apply the changes and display a message.
   */
  setColor (value) {
    this.color = value;
    this.applySettings();
    Application.getInstance().displaySuccessNotification("#settingsNotification", "Nouveaux paramètres enregistré.");
  }

  /**
   * Set the mathSize attribute, apply the changes and display a message.
   */
  setMathSize (value) {
    this.mathSize = value;
    this.applySettings();
    Application.getInstance().displaySuccessNotification("#settingsNotification", "Nouveaux paramètres enregistré.");
  }

  /**
   * Set the timer attribute and display a message.
   */
  setTimer (value) {
    this.timer = value;
    Application.getInstance().displaySuccessNotification("#settingsNotification", "Nouveaux paramètres enregistré.");
  }

  /**
   * Set the autoCloseNotif attribute and display a message.
   */
  setAutoCloseNotif (value) {
    this.autoCloseNotif = value;
    Application.getInstance().displaySuccessNotification("#settingsNotification", "Nouveaux paramètres enregistré.");
  }
}
