/**
 * Class responsible of handling the drag & drop event.
 * Currently not used and not finished.
 */
class DragNDropHandler {

  static setEvents (obj) {
    for (var i in obj.ids)
      $("#" + obj.ids[i]).addClass("dragndrop");

    $(".dragndrop").attr("draggable", "true");
    $(".dragndrop").on("dragstart", DragNDropHandler.dragstartHandler);
    $(".dragndrop").on("dragover", DragNDropHandler.dragoverHandler);
    $(".dragndrop").on("drop", DragNDropHandler.dropHandler);
  }

  static dragstartHandler (event) {
    event.originalEvent.dataTransfer.setData("text/plain", $(this).attr("id"));
  }

  static dragoverHandler (event) {
    event.preventDefault();
  }

  static dropHandler (event) {
    event.preventDefault();
    console.log(event.originalEvent.dataTransfer.getData("text") + "|" + $(this).attr("id"));
    /*$(this).text($("#"+event.originalEvent.dataTransfer.getData("text")).text());
    $("#"+event.originalEvent.dataTransfer.getData("text")).text(tmp);*/
  }
}
