/** Class handling the settings of the application */
class Settings {

  /**
   * @param {string} color color of the background (grandient)
   * @param {string} mathSize font size of the mathJax contained in the div #main-formule
   * @param {bool} timer boolean used to determine if we use a timer or not during the game
   */
  constructor (color, mathSize, timer) {
    this.color = color;
    this.mathSize = mathSize;
    this.timer = timer;
  }

  /**
   * Create a settings object using default value.
   * @return {Settings}
   * @static
   */
  static useDefault () {
    return new Settings ("linear-gradient(to bottom, #1AD6FD, #1D62F0)", "100px", false);
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
}
