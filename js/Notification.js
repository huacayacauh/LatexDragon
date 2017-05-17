/**
 * Class handling notifications
 * @property {Date} date Date at which the notif was issued/emitted/created
 * @property {String} type Type of the notification (it's the color of the notification, correspond to bootstrap colors)
 * @property {String} text Content of the notification
 */
class Notification {

	constructor (type = 'info', text) {
		//date at which the notif was issued/emitted
		this.date = new Date()
		//danger/warning ...
		this.type = type
		//content
		this.text = text
	}

	/**
	 * Function handling the display of the notification
	 * @param {String} element Name of the element to append the notification to (must be a jQuery selector)
	 * @param {int} [lifeTime] Life time of the notification in milliseconds, optional default is 0 which mean the notif won't be automatically closed
	 * @param {String} [mode] Mode of display, if mode is 'replace' the function will try to find any other notifications in element and remove them, if mode is something else the function will just append the notification without deleting anything (default is 'replace')
	 */
	display (element, lifeTime = 0, mode = 'replace') {
		if (mode == 'replace')
			$(element).children('.notif').hide('fast', () => {
				$(this).remove()
			})

		var notif = $('<div></div>').addClass('notif alert alert-' + this.type + ' alert-dismissible').text(this.text).css('display', 'none')

		$('<span></span>').attr('aria-hidden', 'true').append('<i class="fa fa-times" aria-hidden="true"></i>').appendTo(
			$('<button></button>').addClass('close').attr('data-dismiss', 'alert').appendTo(notif)
		)

		$(notif).appendTo(element).show('fast')

		if (lifeTime > 0)
			setTimeout(() => {
				$(notif).hide('fast', function() {
					$(this).remove()
				})
			}, lifeTime)
	}
}

/**
 * Notification module.
 * Check the Notification class for more informations.
 * @module notification
 * @see {@link Notification}
 */
module.exports = Notification
