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
    //Auto close notification on/off
    $("#settings-notifOnOff").change(SettingsHandler.autoCloseNotifHandler);
  }

  /**
   * Set the default values of the element in the tab to match their value
   * in the application settrings
   * @static
   */
  static setValues () {
    $("#settings-color").val(Application.getInstance().settings.color);
    $("#settings-timer").attr("checked", Application.getInstance().settings.timer);
    $("#settings-notifOnOff").attr("checked", Application.getInstance().settings.autoCloseNotif);
    SettingsHandler.memoryUsageHandler();
    SettingsHandler.processInfoHandler();
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

  /**
   * Handler for the notif close on/off select.
   * @static
   */
  static autoCloseNotifHandler () {
    Application.getInstance().settings.setAutoCloseNotif($(this).is(":checked"));
  }

  /**
   * Handler for the memory usage.
   * Memory usage is updated once every 1000 milliseconds.
   * @static
   */
  static memoryUsageHandler () {
    if (Application.getInstance().currentTab == "SETTINGS") {
      var usage = Application.getInstance().getMemoryUsage();

      $("#settings-memoryUsage").html("");
      $("#settings-memoryUsage").append(
        $("<div></div>").text("Actuellement utilisé : ").append($("<b></b>").text(usage.workingSetSize + " Ko"))
      ).append(
        $("<div></div>").text("Maximum : ").append($("<b></b>").text(usage.peakWorkingSetSize + " Ko"))
      ).append(
        $("<div></div>").text("Mémoire caché utilisé : ").append($("<b></b>").text(usage.privateBytes + " Ko"))
      ).append(
        $("<div></div>").text("Mémoire partager utilisé : ").append($("<b></b>").text(usage.sharedBytes + " Ko"))
      );

      setTimeout(SettingsHandler.memoryUsageHandler, 1000);
    }
  }

  /**
   * Handler for the process informations.
   * @static
   */
  static processInfoHandler () {
    var process = Application.getInstance().getProcess();
    const app = Application.getInstance().remote.app;
    $("#settings-processInfo").append(
      $("<div></div>").text("LibreDragon Client version : ").append($("<b></b>").text(app.getVersion()))
    ).append(
      $("<div></div>").text("Process type : ").append($("<b></b>").text(process.type))
    ).append(
      $("<div></div>").text("Electron version : ").append($("<b></b>").text(process.versions.electron))
    ).append(
      $("<div></div>").text("Chrome version : ").append($("<b></b>").text(process.versions.chrome))
    ).append(
      $("<div></div>").text("Node.js version : ").append($("<b></b>").text(process.versions.node))
    );
  }
}
