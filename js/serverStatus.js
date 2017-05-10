const Request = require ('./Request')
const {ipcRenderer} = require('electron')

/**
 * @module serverStatus
 */
var self = module.exports = {

	//true = online, false = offline
	serverStatus: false,

	serverStatusRequest: () => {
		Request.buildRequest('SERVERSTATUS', self.serverStatusReply).send()

		setTimeout(self.serverStatusRequest, 1000)
	},

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
