/**
 * Module containing a bunch of 'useful' functions.
 * @module utils
 */
module.exports = {

	/**
	 * Set the event handler for the buttons controlling the window (close,
	 * maximize and minimize)
	 */
	setWindowControlEvents: () => {
		$('.btn-red').on('click', () => {
			const window = require('electron').remote.getCurrentWindow()
			window.close()
		})

		$('.btn-green').on('click', () => {
			const window = require('electron').remote.getCurrentWindow()
			if (window.isMaximized())
				window.unmaximize()
			else
				window.maximize()
		})

		$('.btn-yellow').on('click', () => {
			const window = require('electron').remote.getCurrentWindow()
			window.minimize()
		})
	},

	/**
	 * Initialize animateCss by adding a function to jQuery handling the animation.
	 */
	initAnimateCss: () => {
		$.fn.extend({
			/**
			 * Animate a DOM element using css animations.
			 * @param {String} animationName Name of the animation, refer to animateCss doc
			 * @param {Number} [duration] duration of the animation in seconds
			 * @param {Number} [delay] delay before the start of the animation in seconds
			 * @param {function} [onanimationend] callback called when the animation is over
			 */
			animateCss: function (animationName, duration, delay, onanimationend) {
				if (duration != undefined)
					$(this).css('-webkit-animation-duration', duration + 's')

				if (delay != undefined)
					$(this).css('-webkit-animation-delay', delay + 's')

				$(this).addClass('animated ' + animationName).one('animationend', () => {
					$(this).removeClass('animated ' + animationName)

					if (onanimationend != undefined)
						onanimationend()
				})
			}
		})
	},

	/**
	 * Call mathJax to typeset any latex elements in the window.
	 * If the elementId parameter is present mathJax will only typeset the element.
	 * @param {function} [callback] fonction to call after the typeset is done
	 * @param {String} [elementId] Id of the dom element to typeset (not a jQuery selector)
	 */
	typesetMath: (callback, elementId) => {
		const instance = require('./Application')

		if (elementId == undefined)
			MathJax.Hub.Queue(['Typeset', MathJax.Hub])
		else
			MathJax.Hub.Queue(['Typeset', MathJax.Hub, elementId])

		if (callback != undefined)
			MathJax.Hub.Queue(callback)
	},

	/**
	 * Write data to a file synchronously or asynchronously to the config/ folder.
	 * @param {String} filename name of the file
	 * @param {String} data data to write
	 * @param {String} [type] synchronous/asynchronous, default is asynchronous
	 */
	writeConfig: (filename, data, type) => {
		const fs = require('fs')
		const appFolder = require('electron').remote.app.getAppPath()

		if ((type == 'async') || (type == undefined))
			fs.writeFile(appFolder + '/config/' + filename, data, (err) => {
				if (err)
					return console.error(err)
			})
		else if (type == 'sync')
			fs.writeFileSync(appFolder + '/config/' + filename, data)
	},

	/**
	 * Read synchronously a file in /config if it exist.
	 * @param {String} filename Name of then file
	 * @return {Object} If the fils exist
	 * @return {null} If the file doesn't exist
	 */
	readConfigSync: (filename) => {
		const fs = require('fs')
		const appFolder = require('electron').remote.app.getAppPath()

		console.log('[CLIENT]: Reading /config/' + filename + ' ...')

		if (fs.existsSync(appFolder + '/config/' + filename))
			return fs.readFileSync(appFolder + '/config/' + filename, 'utf8')
		else
			return null
	}
}
