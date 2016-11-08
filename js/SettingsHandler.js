/** Class containing all the function for handling the elements/event of the settings tab */
class SettingsHandler {

  /**
   * Set the events of the elements in the settings tab.
   * @static
   */
  static setEvents () {
    //Color
    $("#settings-color").change(SettingsHandler.colorHandler);
    //mathJax font size
    $(".settings-mathSize").click(SettingsHandler.mathSizeHandler);
  }

  /**
   * Handler for the color select.
   * @static
   */
  static colorHandler () {
    Application.getInstance().settings.setColor($(this).val());
  }

  /**
   * Handler for the font size select.
   * @static
   */
  static mathSizeHandler () {
    Application.getInstance().settings.setMathSize($(this).text());
  }
}
