function requete () {
  $("#title").text("hjghjgjh");
  var r;
  $.ajax({
    url:"http://localhost:8080/LibreDragon/api/test",
    type:"GET",
    dataType:"html",
    complete: function (xhr, status) {
      console.log("complete : " + status + "\n" + xhr.responseText);
      set_response(xhr.responseText);
    }
  });
}

function set_response (text) {
  $("#title").text(text).hide();
  MathJax.Hub.Queue(["Typeset",MathJax.Hub]);
  MathJax.Hub.Queue(function () {
    $("#title").show();
  });
}
