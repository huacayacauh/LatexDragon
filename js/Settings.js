/** Class handling the settings of the application */
class Settings {

  /**
   * @param {string} color color of the background (grandient)
   * @param {string} mathSize font size of the mathJax contained in the div #main-formule
   */
  constructor (color, mathSize) {
    this.color = color;
    this.mathSize = mathSize;
  }

  /**
   * Create a settings object using default value.
   * @return {Settings}
   * @static
   */
  static useDefault () {
    return new Settings ("linear-gradient(to bottom, #136a8a, #267871)", "100px");
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
    this.settingsChanged();
  }

  /**
   * Set the mathSize attribute, apply the changes and display a message.
   */
  setMathSize (value) {
    this.mathSize = value;
    this.applySettings();
    this.settingsChanged();
  }

  /**
   * Called when an attribute has been changed, display a message for 1500ms and then removes it.
   */
  settingsChanged () {
    $(".alert").show(500);
    setTimeout (function () {
      $(".alert").hide(500);
    }, 1500);
  }
}
