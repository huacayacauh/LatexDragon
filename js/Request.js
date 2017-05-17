const EnumHelper = require ('./EnumHelper')

/**
 * Class Request, used to send HTTP request to the Java rest api
 *
 */
class Request {
	/**
	 * @param {String} serverUrl server url to which the request is send to
	 * @param {String} type type of the http request (GET, POST ...)
	 * @param {String} dataType type of the response (HTML, JSON, TEXT ...)
	 * @param {function} [responseHandler] function called when the request is completed, can be omitted
	 */
	constructor (serverUrl, type, dataType, responseHandler) {
		this.serverUrl = serverUrl
		this.type = type
		this.dataType = dataType
		if (responseHandler == undefined)
			this.responseHandler = undefined
		else
			this.responseHandler = responseHandler
		this.responseMessage = undefined
	}

	/**
	 * Execute an asynchronous HTTP request to the server and call this.response() when complete.
	 * @param {String} [item] string of arguments that need to be sent
	 */
	send (item) {
		var self = this
		var url = this.serverUrl

		if (item != undefined)
			url = this.serverUrl + item

		$.ajax({
			url:url,
			type:this.type,
			dataType:this.dataType,
			complete: function (response, status) {
				if (item)
					console.log('[REQUEST]: ' + new Date().toString() + '\nUrl:' + self.serverUrl + item + '\nRetrurned status : ' + response.status + '|' + status)
				else
					console.log('[REQUEST]: ' + new Date().toString() + '\nUrl:' + self.serverUrl + '\nRetrurned status : ' + response.status + '|' + status)
				self.response(response, status)
			}
		})
	}

	/**
	 * Store the response from the request, and if defined call the responseHandler function.
	 * @param {Object} response response from the request (jQuery ajax response)
	 * @param {String} status status response from the request
	 */
	response (response, status) {
		if (this.dataType == 'text')
			console.log('[REQUEST]: Message : ' + response.responseText)

		this.responseMessage = response

		if (this.responseHandler != undefined)
			this.responseHandler(response, status)
	}

	/**
	 * Static method that create a request using EnumHelper.
	 * @param {String} requestName name of the request defined in EnumHelper
	 * @param {function} [responseHandler] function called when the request is completed, can be omitted
	 * @return {Request}
	 * @static
	 */
	static buildRequest (requestName, responseHandler) {
		return new Request (EnumHelper.REQUESTS[requestName].url, EnumHelper.REQUESTS[requestName].type, EnumHelper.REQUESTS[requestName].dataType, responseHandler)
	}

	/**
	 * Check if the request was successfull. Check first the status and then try to convert
	 * the response to a JSON object to check for additional informations.
	 * @param {Object} response response from the request (jQuery ajax response)
	 * @param {String} status status response from the request
	 * @param {String} [element] dom element used to display the notification
	 * @return {Object} return the JSON object if there's no error.
	 * @return {bool} return false if there's an error.
	 * @static
	 */
	static checkError (response, status, element) {
		const instance = require('./Application')

		if (status != 'success') {
			instance.displayErrorNotification(element, 'Erreur lors de la requête, status : ' + status + ' (' + response.status + ').')
			return false
		}
		else {
			try {
				var o = JSON.parse(response.responseText)

				if (o && typeof o === 'object') {
					if (o.status != 'SUCCESS') {
						instance.displayErrorNotification(element, o.complementaryInfo)
						return false
					}
					return o
				}
			}
			catch (e) {	}
		}
		return false
	}
}

/**
 * Request module.
 * Check the Request class for more informations.
 * @module request
 * @see {@link Request}
 */
module.exports = Request
