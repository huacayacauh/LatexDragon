const utils = require('./utils')
const Request = require('./Request')
const GameState = require('./GameState')
const {ipcRenderer} = require('electron')

/**
 * module containing all the function for handling the synchronizing of the games with the server
 * @module synchronize
 */
var self = module.exports = {

	toSynchronize: [],

	areSynchronied: [],

	requestDone: 0,

	synchronize: () => {
		var data = utils.readConfigSync('gamestate.json')

		if (data != null) {
			data = JSON.parse(data)

			self.toSynchronize = []

			for (var i in data) {
				var game = GameState.getNewGame()
				Object.assign(game, data[i])

				if (game.isComplete())
					self.toSynchronize.push(game)
			}

			self.areSynchronied = []

			for (var i in self.toSynchronize)
				Request.buildRequest('RESUME', self.synchronizeReply).send('/' + self.toSynchronize[i].gameId)

			if (self.toSynchronize == 0)
				self.synchronizationDone()
		}
	},

	synchronizeReply: (response, status) => {
		if (status == 'success') {
			var obj = JSON.parse(response.responseText)


			if (obj.status == 'SUCCESS') {
				for (var i in self.toSynchronize) {
					if (self.toSynchronize[i].gameId == obj.id)
						self.areSynchronied.push(self.toSynchronize[i])
				}
			}
		}
		self.requestDone++

		if (self.requestDone == self.toSynchronize.length)
			self.synchronizationDone()
	},

	synchronizationDone: () => {
		utils.writeConfig('gamestate.json', JSON.stringify(self.areSynchronied, null, 2), 'sync')
		ipcRenderer.send('synchronization-done')
		ipcRenderer.send('boom')
	}
}
