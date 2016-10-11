/** Class handling all mouse click related events */
class MouseClickHandler {

  /**
   * Set the events for the elements in obj
   * @param {Object} obj json object recieved from a request
   */
  static setEvents (obj) {
    for (var i in obj.ids) {
        $("#"+obj.ids[i]).on("mouseenter", { value:obj.list }, MouseClickHandler.mouseenterHandler);
        $("#"+obj.ids[i]).on("mouseleave", MouseClickHandler.mouseleaveHandler);
        $("#"+obj.ids[i]).on("contextmenu", { value:obj }, MouseClickHandler.contextmenuHandler);
    }
  }

  /**
   * Handler of the mouseenter event, when triggered create the tooltip and displays it
   * @param {Event} event jQuery Event object
   */
  static mouseenterHandler (event) {
    var id = $(this).attr("id")

    MouseClickHandler.getTooltipList(event.data.value, id);

    event.stopPropagation();

    $("#tooltip").show();
    $("#tooltip").css("top", event.pageY+20);
    $("#tooltip").css("left", event.pageX+10);
  }

  /**
   * Handler of the mouseleave event, when triggered hide the tooltip
   * @param {Event} event jQuery Event object
   */
  static mouseleaveHandler (event) {
    event.stopPropagation();
    $("#tooltip").hide();
  }

  /**
   * Handler of the contextmenu event, when triggered create the tooltip and displays it
   * but also deactivate the mouseenter and mouseleave handlers
   * @param {Event} event jQuery Event object
   */
  static contextmenuHandler (event) {
    var id = $(this).attr("id");

    MouseClickHandler.getTooltipList(event.data.value.list, id);

    event.stopPropagation();

    $("#tooltip").show();
    $("#tooltip").css("top", event.pageY+20);
    $("#tooltip").css("left", event.pageX+10);

    for (var i in event.data.value.ids) {
      $("#" + event.data.value.ids[i]).off("mouseenter");
      $("#" + event.data.value.ids[i]).off("mouseleave");
    }
  }

  /**
   * Create the list of elements that the tooltip displays
   * @param {Object} list list of elements to be displayed in the tooltip
   * @param {string} id id of the elment who triggered the event, used to display the correct informations
   */
  static getTooltipList (list, id) {
    var options;
    for (var i in list) {
      if (list[i][id] != undefined)
        options = list[i][id];
    }

    $("#tooltipList").html("");
    for (var i in options) {
      $("#tooltipList").append("<li><a>" + options[i] + "</a></li>");
    }
  }

  /**
   * Function handling the closing of the tooltip after the oncontextmenu event
   * and reseting the handlers
   * @param {Event} event jQuery Event object
   */
  static bodyTooltipHandler (event) {
    event.stopPropagation();
    if ($("#tooltip").is(":visible")) {
      $("#tooltip").hide();
      MouseClickHandler.setEvents(req);
    }
  }
}
