$.getScript("js/Request.js");

//Todo : EnumHelper for requests comming from a config file or smth
function requete () {
  $("#title").text("hjghjgjh");
  var request = new Request ("http://localhost:8080/LibreDragon/api/test", "GET", "html", set_response);
  request.send();
}

function set_response (response) {
  $("#main_formule").text(response.responseText).hide();
  MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
  MathJax.Hub.Queue(function () {
    $("#main_formule").show();
  });
}
