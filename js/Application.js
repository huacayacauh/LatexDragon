const {BrowserWindow} = require('electron').remote;
var req;
var loadedScripts = 0, scriptsToLoad = 6;
loadScripts();

//TODO: gameHandler qui s'occupe de DragNDropHandler et MouseClickHandler et cleanup les requetes (bessoin de finir les règles côté serveur d'abord)

/**
 * Class controlling the different tab of the application.
 * The application is divided in 3 (4 counting the doc) "tab".
 * This class is used to switch between tab and to initialize everything the tabs
 * need in order to run.
 * Can be considered as the "main" object running the application.
 */
class Application {

  /**
   * There can be only one instance of Application, if no instance exist a new
   * is created, if one instance already exist then it's the one returned.
   */
  constructor () {
    if (!Application.instance) {
      this.settings = Settings.useDefault();
      this.currentTab = "GAME";
      this.tabContainer = $(".main")[0];
      this.windows = {
        "app" : BrowserWindow.getFocusedWindow(),
        "doc" : null
      };
      this.loader = $("<div></div>").addClass("spinner");
      //this.json = null;
      Application.instance = this;
    }

    return Application.instance;
  }

  /**
   * Send a request to get an html file, and change the active tab.
   * Used to get the diffrent html file composing the app "tabs".
   * Call loadHtml() when completed.
   * @param {String} string Name of the request (in this case it's also the name of the tab)
   */
  requestHtml (string) {
    var request = Request.buildRequest(string, this.loadHtml);
    $(".main").html(this.loader);
    request.send();
    this.currentTab = string;
  }

  /**
   * Callback function of the requestHtml request.
   * Load the html file recieved onto the main element.
   * @param {Object} response response from the request (jQuery ajax response)
   */
  loadHtml (response) {
    var htmlpage = $(response.responseText);
    $(".main").hide();
    $(".main").html("");
    $(".main").append(htmlpage);
    $(".main").show(400);
    console.log("[CLIENT]: Tab " + Application.getInstance().currentTab + " loaded");
    //Need to use this since in the context when the function is called "this" reference the request object and not the appliction object
    Application.getInstance().setNavbarActive();
    Application.getInstance().loaded();
  }

  /**
   * Control the active tab of the navbar header.
   * Remove the old active element and set the element with the id string to active.
   */
  setNavbarActive () {
    var id = this.currentTab.toLowerCase();
    $("#" + id).parent().find("li").removeClass("active");
    $("#" + id).addClass("active");
  }

  /**
   * Called when the tab is loaded.
   * Initialize everything the tab need in order to running,
   * also apply the settings to the tab newly loaded.
   */
  loaded () {
    if (this.currentTab == "GAME")
      DragNDropHandler.setEvents();
    else if (this.currentTab == "SETTINGS")
      SettingsHandler.setEvents();

    this.settings.applySettings();
  }

  /**
   * Return the only instance of Application.
   * @static
   */
  static getInstance () {
    return Application.instance;
  }

  /**
   * Display chromium dev tools on the app window.
   */
  displayConsole() {
    this.windows["app"].webContents.openDevTools();
  }

  /**
   * Create a new window and use it to display the documentation.
   * Will focus on the doc window if already open.
   */
  showDoc () {
    if (this.windows["doc"] == null) {
      this.windows["doc"] = new BrowserWindow({width:1280, height:640});

      this.windows["doc"].setMenu(null);

      this.windows["doc"].loadURL(`file://${__dirname}/out/index.html`);

      this.windows["doc"].on('closed', () => {
        this.windows["doc"] = null;
      });
    }
    else
      this.windows["doc"].focus();
  }
}

/**
 * Called when the document finished loading and is ready.
 * Check if all the scripts have been loaded sucessfully.
 * Request the game html file (By default the app start on the game "tab")
 */
$(document).ready (function () {
  console.log("[CLIENT]: Document ready");
  if (loadedScripts != scriptsToLoad)
      console.log("[CLIENT]: Failed to load all scripts");
  console.log("[CLIENT]: Scripts loading complete (loaded "+loadedScripts+"/"+scriptsToLoad+")");
  var application = new Application();
  application.requestHtml("GAME");
});


function requete () {
  //Set body handler for tooltip
  $("body").on("contextmenu", MouseClickHandler.bodyTooltipHandler);
  $("body").on("click", MouseClickHandler.bodyTooltipHandler);
  var request = Request.buildRequest("TEST1", set_response);
  request.send();
}

function set_response (response) {
  req = JSON.parse(response.responseText);

  $(".jumbotron:visible").hide();

  $("#main-formule").text(req.math).hide();

  MathJax.Hub.Queue(["Typeset",MathJax.Hub]);

  MathJax.Hub.Queue(function () {
    $("#main-formule").show();
    MouseClickHandler.setEvents(req);
  });
}

/**
 * Load all the needed javascript scripts.
 */
function loadScripts () {
  $.getScript("js/Request.js").done(function () { loadedScripts++; });
  $.getScript("js/EnumHelper.js").done(function () { loadedScripts++; });
  $.getScript("js/MouseClickHandler.js").done(function () { loadedScripts++; });
  $.getScript("js/Settings.js").done(function () { loadedScripts++; });
  $.getScript("js/SettingsHandler.js").done(function () { loadedScripts++; });
  $.getScript("js/DragNDropHandler.js").done(function () { loadedScripts++; });
}
