const {BrowserWindow} = require('electron').remote;
var req;
var loadedScripts = 0, scriptsToLoad = 4;
let doc;
loadScripts();

/**
 * Called when the document finished loading and is ready
 * Check if all the scripts have been loaded sucessfully
 * Request the game html file (By default the app start on the game "tab")
 */
$(document).ready (function () {
  console.log("[CLIENT]: Document ready");
  if (loadedScripts != scriptsToLoad)
      console.log("[CLIENT]: Failed to load all scripts");
  console.log("[CLIENT]: Scripts loading complete (loaded "+loadedScripts+"/"+scriptsToLoad+")");
  requestHtml("GAME");
});


function requete () {
  //Set body handler for tooltip
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

/**
 * Load all the needed javascript scripts
 */
function loadScripts () {
  $.getScript("js/Request.js").done(function () { loadedScripts++; });
  $.getScript("js/EnumHelper.js").done(function () { loadedScripts++; });
  $.getScript("js/MouseClickHandler.js").done(function () { loadedScripts++; });
  $.getScript("js/LoadBar.js").done(function () { loadedScripts++; });
}

/**
 * Send a request to get an html file, and change the active tab on the navbar
 * Used to get the diffrent html file composing the app (the "tabs")
 * Call loadHtml() when complete
 * @param {String} string Name of the request
 */
function requestHtml (string) {
  var request = Request.buildRequest(string, loadHtml);
  request.send();
  setNavbarActive(string);
}

/**
 * Callback function of the requestHtml request
 * Load the html file recieved onto the main element
 * @param {Object} response response from the request (jQuery ajax response)
 */
function loadHtml (response) {
  var htmlpage = $(response.responseText);
  $(".main").hide();
  $(".main").html("");
  $(".main").append(htmlpage);
  $(".main").show(400);
}

/**
 * Control the active tab of the navbar header
 * Remove the old active element and set the element with the id string to active
 * @param {String} string Name of the request, used to get the id
 */
function setNavbarActive (string) {
  var id = string.toLowerCase();
  $("#" + id).parent().find("li").removeClass("active");
  $("#" + id).addClass("active");
}

/**
 * Create a new window and use it to display the documentation
 */
function showDoc () {
  doc = new BrowserWindow({width:1280, height:640});

  doc.setMenu(null);

  doc.loadURL(`file://${__dirname}/out/index.html`)

  doc.on('closed', () => {
    doc = null
  });
}
