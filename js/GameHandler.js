/** Class containing all the function for handling the elements/event of the game tab */
class GameHandler {

  /**
   * Set the events for the elements in obj.
   * @static
   */
  static setEvents (obj) {
    MouseClickHandler.setEvents(obj);
    DragNDropHandler.setEvents(obj);
    console.log(obj);
  }

  /**
   * Request used to start a new game, will ask the server to start a new game
   * with the mathId formula.
   * @param {String} mathId id of the formula the user want to play with
   * @static
   */
  static startNewGame (mathId) {
    var request = Request.buildRequest("START", GameHandler.startNewGameResponse);

    Application.getInstance().formulaId = mathId;

    request.send("/" + mathId);
  }

  /**
   * Response of the startNewGame request.
   * Will display an error message if the server can't create a new game.
   * If it's a success it will register the game ID and send a request to get the
   * game state.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status response status from the request
   * @static
   */
  static startNewGameResponse (response, status) {
    //Request error
    if (status != "success")
      Application.getInstance().displayErrorNotification("#gameNotification", "Erreur lors de la requête, status : " + status + " (" + response.status + ").");

    var obj = JSON.parse(response.responseText);

    //Server error
    if (obj.status == "FAILURE")
      Application.getInstance().displayErrorNotification("#gameNotification", obj.complementaryInfo);

    Application.getInstance().gameId = obj.id;

    var request = Request.buildRequest("GAMESTATE", GameHandler.gameStartResponse);
    request.send("/" + obj.id);
  }

  /**
   * Response of the request to get the game state after a new game is created.
   * Will start a timer if it's enabled and delegate the processing of the response
   * to gameUpdateMathResponse.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status response status from the request
   * @static
   */
  static gameStartResponse (response, status) {
    GameHandler.gameUpdateMathResponse(response, status);
    var app = Application.getInstance();

    //Start timer
    if (app.settings.timer) {
      if (app.countdown != null)
        app.countdown.stopCountdown();
      app.countdown = new Countdown (Countdown.minutesToMilliseconds(1), GameHandler.timerOnOver, GameHandler.timerOnUpdate);
      app.countdown.startCountdown();
    }

    //Hide start button
    $(".jumbotron:visible").hide();

    //Show game tools
    $("#tools").fadeIn(800);
  }

  /**
   * Send a request to get the game state.
   * @static
   */
  static gameStateRequest () {
      var request = Request.buildRequest("GAMESTATE", GameHandler.gameUpdateMathResponse);
      request.send();
  }

  /**
   * Function that process the response from the server containing the formula state.
   * Will throw an error if the request failed.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status response status from the request
   * @throws if the request fail and the function receive a status diffrent than success.
   * @static
   */
  static gameUpdateMathResponse (response, status) {
    if (status == "success")
      Application.getInstance().json = JSON.parse(response.responseText);
    else {
      Application.getInstance().displayErrorNotification("#gameNotification", "Erreur lors de la requête, status : " + status + " (" + response.status + ").");
      throw "[ERROR]: request response invalid, request might have failed.";
    }

    //Set new math
    $("#main-formule").text(Application.getInstance().json.math).hide();

    //Call mathJax typeset and show the formule once it's done
    GameHandler.typesetMath(() => {
      $("#main-formule").show();
      //Set events
      GameHandler.setEvents(Application.getInstance().json);
    });
  }

  /**
   * Send a request to the server to apply a rule to the formula.
   * Q
   * @param {Event} event jQuery Event object
   */
  static gameRuleRequest (event) {
    event.stopPropagation();
    var request = Request.buildRequest("APPLYRULE", GameHandler.gameUpdateMathResponse);
    request.send("/" + Application.getInstance().gameId + "/" + event.data.value.expId + "/" + event.data.value.ruleId + "/" + event.data.value.context);

    if ($("#tooltip").is(":visible"))
      $("#tooltip").hide(100);
  }

  static typesetMath (callback) {
    MathJax.Hub.Queue(["Typeset", MathJax.Hub]);

    if (callback != undefined)
      MathJax.Hub.Queue(callback);
  }

  static timerOnOver () {
    $("#tools").hide(800);
    $("#gameTimer").html("");
    Application.getInstance().displaySuccessNotification("Temps écouler, partie finie.");
    Application.getInstance().countdown = null;
    Request.buildRequest("OVER").send(GameHandler.gameOverResponse);
  }

  static gameOverResponse (response, status) {
    if (status != "success") {
      Application.getInstance().displayErrorNotification("#gameNotification", "Erreur lors de la requête, status : " + status + " (" + response.status + ").");
      throw "[ERROR]: request response invalid, request might have failed.";
    }

    $("#tools").hide();

    $("#main-formule").hide();

    $(".jumbotron:visible").show("slow");

  }

  static timerOnUpdate (countdown) {
    $("#gameTimer").html($("<h1></h1>").text(countdown.toString()));
  }
}
