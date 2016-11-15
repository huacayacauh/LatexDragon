class Countdown {

  constructor (duration, overHandler, updateHandler) {
    this.remainingTime;
    this.end;
    this.duration = duration;
    this.onOver = overHandler;
    this.onUpdate = updateHandler;
  }

  startCountdown () {
    this.end = Date.now() + this.duration;
    Countdown.updateCountdown(this);
  }

  static updateCountdown (countdown) {
    var time = Date.now();

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

  toString () {
    var date = new Date(this.remainingTime);
    return "Temps restant : " + date.getMinutes() + ":" + date.getSeconds();
  }

  static minutesToMilliseconds (minutes) {
    return minutes * 60 * 1000;
  }
}
