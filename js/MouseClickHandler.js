/** Class containing handler for all mouse click related events in the game tab */
class MouseClickHandler {

  /**
   * Set the events for the elements in obj.
   * @param {Object} obj json object recieved from a request
   * @static
   */
  static setEvents (obj) {
    //Set body handler for tooltip
    $("body").on("contextmenu", MouseClickHandler.bodyTooltipHandler);
    $("body").on("click", MouseClickHandler.bodyTooltipHandler);

    for (var i in obj.ids) {
        var test = $("#"+obj.ids[i]).on("mouseover", { value:obj.rules }, MouseClickHandler.mouseoverHandler);
        $("#"+obj.ids[i]).on("mouseleave", MouseClickHandler.mouseleaveHandler);
        $("#"+obj.ids[i]).on("mousemove", MouseClickHandler.mousemoveHandler);
        $("#"+obj.ids[i]).on("contextmenu", { value:obj }, MouseClickHandler.contextmenuHandler);
    }
  }

  /**
   * Handler of the mouseenter event, when triggered create the tooltip and displays it.
   * @param {Event} event jQuery Event object
   * @static
   */
  static mouseoverHandler (event) {
    event.stopPropagation();

    var id = $(this).attr("id");

    //MouseClickHandler.getTooltipList(event.data.value, id);

    $("#tooltip").show(100);
    $("#tooltip").css("top", event.pageY+20);
    $("#tooltip").css("left", event.pageX+10);
  }

  /**
   * Handler of the mouseleave event, when triggered hide the tooltip.
   * @param {Event} event jQuery Event object
   * @static
   */
  static mouseleaveHandler (event) {
    event.stopPropagation();

    $("#tooltip").hide(100);
  }

  /**
   * Handler of the mousemove event, when triggered update the position of the tooltip.
   * @param {Event} event jQuery Event object
   * @static
   */
  static mousemoveHandler (event) {
    event.stopPropagation();

    $("#tooltip:visible").css("top", event.pageY+20);
    $("#tooltip:visible").css("left", event.pageX+10);
  }

  /**
   * Handler of the contextmenu event, when triggered create the tooltip and displays it
   * but also deactivate the mouseover, mouseleave and mousemove handlers.
   * @param {Event} event jQuery Event object
   * @static
   */
  static contextmenuHandler (event) {
    event.stopPropagation();
    console.log("menu");
    var id = $(this).attr("id");

    MouseClickHandler.getTooltipList(event.data.value.rules, id);

    $("#tooltip").show(100);
    $("#tooltip").css("top", event.pageY+20);
    $("#tooltip").css("left", event.pageX+10);

    for (var i in event.data.value.ids) {
      $("#" + event.data.value.ids[i]).off("mouseover");
      $("#" + event.data.value.ids[i]).off("mouseleave");
      $("#" + event.data.value.ids[i]).off("mousemove");
    }
  }

  /**
   * Create the list of elements that the tooltip displays.
   * @param {Object} list list of elements to be displayed in the tooltip
   * @param {string} id id of the elment who triggered the event, used to display the correct informations
   * @static
   */
  static getTooltipList (list, id) {
    var options = new Array();

    for (var i in list) {
      if (list[i][id] != undefined) {
        for (var j in list[i][id]) {
          if (list[i][id][j].type == "contextMenu")
            options.push(list[i][id][j]);
        }
      }
    }

    $("#tooltipList").html("");
    for (var i in options) {
      var obj = {expId:id, ruleId:options[i].ruleId, context:options[i].type};
      var tmp = $("<li><a>" + options[i].text + "</a></li>").on("click", { value:obj }, GameHandler.gameRuleRequest);
      $("#tooltipList").append(tmp);
    }
  }

  /**
   * Function handling the closing of the tooltip after the oncontextmenu event
   * and reseting the handlers.
   * @param {Event} event jQuery Event object
   * @static
   */
  static bodyTooltipHandler (event) {
    if ($("#tooltip").is(":visible")) {
      $("#tooltip").hide(100);
      for (var i in Application.getInstance().json.ids)
        $("#" + Application.getInstance().json.ids[i]).off("contextmenu");
      MouseClickHandler.setEvents(Application.getInstance().json);
    }
  }
}
