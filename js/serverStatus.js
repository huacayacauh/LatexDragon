const Request = require ('./Request')
const {ipcRenderer} = require('electron')

/**
 * Module used to keep in check the status of the server.
 * This module will spam requests and also spam your console of messages.
 * Preferably used in a background process.
 * @module serverStatus
 */
var self = module.exports = {

	//true = online, false = offline
	serverStatus: false,

	/**
	 * Send a request SERVERSTATUS and set a timeout to send another one every 1 seconds.
	 */
	serverStatusRequest: () => {
		Request.buildRequest('SERVERSTATUS', self.serverStatusReply).send()

		setTimeout(self.serverStatusRequest, 1000)
	},

	/**
	 * Reply of the SERVERSTATUS request.
	 * Will update serverStatus and if the status change will send a 'server-status'
	 * event to the main process.
	 * @param {Object} response response from the request (jQuery ajax response)
	 * @param {String} status response status from the request
	 */
	serverStatusReply: (response, status) => {
		if (status != 'success') {
			if (self.serverStatus)
				ipcRenderer.send('server-status', false)
			self.serverStatus = false
		}
		else {
			if (!self.serverStatus)
				ipcRenderer.send('server-status', true)
			self.serverStatus = true
		}
	}
}
