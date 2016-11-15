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
    //Timer on/off
    $("#settings-timer").change(SettingsHandler.timerHandler);
  }

  /**
   * Set the default values of the element in the tab to match their value
   * in the application settrings
   * @static
   */
  static setValues () {
    $("#settings-color").val(Application.getInstance().settings.color);
    $("#settings-timer").attr("checked", Application.getInstance().settings.timer);
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

  /**
   * Handler for the timer on/off select.
   * @static
   */
  static timerHandler () {
    Application.getInstance().settings.setTimer($(this).is(":checked"));
  }
}
