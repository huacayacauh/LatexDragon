/** Class containing all the function for handling the elements/event of the game tab */
class GameHandler {

  /**
   * Request used to start a new game, will ask the server to start a new game
   * with the mathId formula.
   * @param {String} mathId id of the formula the user want to play with
   * @static
   */
  static startNewGame (mathId) {
    var request = Request.buildRequest("START", GameHandler.startNewGameResponse);
    request.send("/" + mathId);
  }

  /**
   * Response of the startNewGame request.
   * Will display an error message if the server can't create a new game.
   * If it's a success, ...
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

    //Start timer
    if (Application.getInstance().settings.timer) {
      var timer = new Countdown (Countdown.minutesToMilliseconds(1), GameHandler.timerOver, GameHandler.updateTimer);
      timer.startCountdown();
    }

    //Show game tools
    $("#tools").fadeIn(800);

    //TODO: add id to application.gameId
  }

  static timerOver () {
    $("#tools").hide(800);
    $("#gameTimer").html("");
    alert ("timer over");
  }

  static updateTimer (countdown) {
    $("#gameTimer").html($("<h1></h1>").text(countdown.toString()));
  }
}
