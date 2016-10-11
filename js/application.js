loadScripts();
var req;

//Todo : EnumHelper for requests comming from a config file or smth
function requete () {
  $("body").on("contextmenu", MouseClickHandler.bodyTooltipHandler);
  $("body").on("click", MouseClickHandler.bodyTooltipHandler);
  $("#title").text("hjghjgjh");
  var request = Request.buildRequest("TEST1", set_response);
  request.send();
}

function set_response (response) {
  req = JSON.parse(response.responseText);

  $("#main_formule").text(req.math).hide();
  MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
  MathJax.Hub.Queue(function () {
    $("#main_formule").show();
    MouseClickHandler.setEvents(req);
  });
}

function loadScripts () {
  $.getScript("js/Request.js");
  $.getScript("js/EnumHelper.js");
  $.getScript("js/MouseClickHandler.js");
  /*setTimeout(function() {
    for (var i=0;i<=10;i++) {
      console.log(i);
      $("#loadbar").css("width", i*10+"%");
      $("#loadbar").text((i*10) + "%");
    }
    setTimeout(function () {
      $(".progress").hide();
    },1000);
  }, 2000);*/
  console.log("[CLIENT]: Scripts loading complete");
}
