class DragNDropHandler {

  static setEvents () {
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
    var tmp = $(this).text();
    $(this).text($("#"+event.originalEvent.dataTransfer.getData("text")).text());
    $("#"+event.originalEvent.dataTransfer.getData("text")).text(tmp);
  }
}
