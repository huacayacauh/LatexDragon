/** Class managing a countdown */
class Countdown {

  /*
   * @param {int} duration duration of the ocuntdowns (in milliseconds)
   * @param {function} [overHandler] function called when the countdown is over
   * @param {function} [updateHandler] function called each time the countdown update
   */
  constructor (duration, overHandler, updateHandler) {
    this.remainingTime;
    this.end;
    this.duration = duration;
    this.onOver = overHandler;
    this.onUpdate = updateHandler;
    this.state = "NOT_STARTED";
  }

  /*
   * Funciton used to start the countdown if it's not already started.
   * @throws Will throw an error if the countdown is already started
   */
  startCountdown () {
    if (this.state == "NOT_STARTED") {
      this.end = Date.now() + this.duration;
      this.state = "STARTED";
      Countdown.updateCountdown(this);
    }
    else
      throw "[ERROR]: Countdown already started.";
  }

  /*
   * Function updating the countdown, only if the countdown is started.
   * @param {Countdown} countdown countdown object to update
   * @throws Will throw an error if the countdown is not started
   * @static
   */
  static updateCountdown (countdown) {
    var time = Date.now();

    if (countdown.state == "STARTED") {
      if (time >= countdown.end) {
        console.log("[CLIENT]: Countdown fini.");
        if (countdown.onOver != undefined)
          countdown.onOver(countdown);
      }
      else {
        countdown.remainingTime = countdown.end - time;
        if (countdown.onUpdate != undefined)
          countdown.onUpdate(countdown);
        setTimeout(Countdown.updateCountdown, 1000, countdown);
      }
    }
    else
      throw "[ERROR]: Countdown not started."
  }

  /*
   * Convert the countdown in a readable string.
   * @returns {String}
   */
  toString () {
    var date = new Date(this.remainingTime);
    return "Temps restant : " + date.getMinutes() + ":" + date.getSeconds();
  }

  /*
   * Convert minutes in to milliseconds.
   * @param {int} minutes minutes to convert
   * @returns {int} milliseconds
   * @static
   */
  static minutesToMilliseconds (minutes) {
    return minutes * 60 * 1000;
  }
}
