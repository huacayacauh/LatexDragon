class Notification {

	constructor (type = 'info', text) {
		//date at which the notif was issued/emitted
		this.date = new Date()
		//danger/warning ...
		this.type = type
		//content
		this.text = text
	}

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
 * @module notification
 * @see {@link Notification}
 */
module.exports = Notification
