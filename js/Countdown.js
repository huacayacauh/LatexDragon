/** Class managing a countdown */
class Countdown {

	/**
	 * @param {int} duration duration of the countdown (in milliseconds)
	 * @param {function} [overHandler] function called when the countdown is over
	 * @param {function} [updateHandler] function called each time the countdown update
	 * @param {int} [remainingTime] remaining time, optional used to create and already started countdown
	 */
	constructor (duration, overHandler, updateHandler, remainingTime) {
		if (remainingTime != undefined) {
			this.remainingTime = remainingTime
			this.state = 'STOPPED'
		}
		else {
			this.remainingTime
			this.state = 'NOT_STARTED'
		}

		this.end
		this.duration = duration
		this.onOver = overHandler
		this.onUpdate = updateHandler
	}

	/**
	 * Function used to start the countdown.
	 * @throws Will throw an error if the countdown is over
	 */
	startCountdown () {
		if (this.state == 'NOT_STARTED') {
			this.end = Date.now() + this.duration
			this.state = 'STARTED'
			Countdown.updateCountdown(this)
		}
		else if (this.state == 'STOPPED') {
			this.end = Date.now() + this.remainingTime
			this.state = 'STARTED'
			Countdown.updateCountdown(this)
		}
		else
			throw '[ERROR]: Countdown already started.'
	}

	/**
	 * Function updating the countdown, only if the countdown is started.
	 * @param {Countdown} countdown countdown object to update
	 * @throws Will throw an error if the countdown is over
	 * @static
	 */
	static updateCountdown (countdown) {
		var time = Date.now()

		this.end = time + this.remainingTime

		if (countdown.state == 'STARTED') {
			if (time >= countdown.end) {
				console.log('[CLIENT]: Countdown fini.')
				countdown.state = 'OVER'
				if (countdown.onOver != undefined)
					countdown.onOver(countdown)
			}
			else {
				countdown.remainingTime = countdown.end - time
				if (countdown.onUpdate != undefined)
					countdown.onUpdate(countdown)
				setTimeout(Countdown.updateCountdown, 1000, countdown)
			}
		}
		else if (countdown.state == 'OVER')
			throw '[ERROR]: Countdown over can\'t update.'
	}

	/**
	 * Stop the countdown.
	 */
	stopCountdown () {
		this.state = 'STOPPED'
	}

	/**
	 * Return the time elapsedTime since the start of the countdown.
	 */
	timeElapsed () {
		if (this.state == 'NOT_STARTED')
			return '00:00'

		var elapsedTime = new Date(this.duration - this.remainingTime)

		var minutes = elapsedTime.getMinutes()
		var seconds = elapsedTime.getSeconds()

		if (minutes.toString().length == 1)
			minutes = '0' + minutes

		if (seconds.toString().length == 1)
			seconds = '0' + seconds

		return minutes + ':' + seconds
	}

	/**
	 * Convert the countdown into a readable string.
	 * @returns {String}
	 */
	toString () {
		var date = new Date(this.remainingTime)
		var minutes = date.getMinutes()
		var seconds = date.getSeconds()

		if (minutes.toString().length == 1)
			minutes = '0' + minutes

		if (seconds.toString().length == 1)
			seconds = '0' + seconds

		return minutes + ':' + seconds
	}

	/**
	 * Function called when the object is serialized.
	 */
	toJSON () {
		return {
			'duration': this.duration,
			'remainingTime': this.remainingTime
		}
	}

	/**
	 * Convert minutes in to milliseconds.
	 * @param {int} minutes minutes to convert
	 * @returns {int} milliseconds
	 * @static
	 */
	static minutesToMilliseconds (minutes) {
		return minutes * 60 * 1000
	}
}

/**
 * Countdown module.
 * Check the Countdown class for more informations.
 * @module countdown
 * @see {@link Countdown}
 */
module.exports = Countdown
