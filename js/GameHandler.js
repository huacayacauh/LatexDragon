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
   * @static
   */
  static startNewGameResponse (response) {
    var obj = JSON.parse(response.responseText);
    if (obj.status == "FAILURE")
      Application.getInstance().displayErrorNotification("#gameNotification", obj.complementaryInfo);

    if (Application.getInstance().settings.timer) {
      var timer = new Countdown (Countdown.minutesToMilliseconds(1), GameHandler.timerOver, GameHandler.updateTimer);
      timer.startCountdown();
    }
    $("#tools").fadeIn(800);
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
