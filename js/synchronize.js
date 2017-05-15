const utils = require('./utils')
const Request = require('./Request')
const GameState = require('./GameState')
const {ipcRenderer} = require('electron')

/**
 * Module containing all the function for handling the synchronizing of the games with the server
 * This module might spam requests and also spam your console of messages.
 * Preferably used in a background process.
 * @module synchronize
 */
var self = module.exports = {

	/** array of games to synchronize */
	toSynchronize: [],

	/** games that are synchronized */
	areSynchronied: [],

	/** Number of request finished */
	requestDone: 0,

	/**
	 * The function will read the config/gamestate.json file to get the games and
	 * then will send a request to synchronize each game.
	 */
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

	/**
	 * Reply of the synchronize request(s).
	 * If the request is a success the game will be added to the array of game
	 * synchronized.
	 * If all requests are done will call the synchronizationDone function.
	 */
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

	/**
	 * Function called when everyting is done.
	 * Will write the areSynchronied array to the config/gamestate.json file, then
	 * send a 'synchronization-done' event to the main process and finally a 'boom'
	 * event to tell the main process to close this one because his task is done.
	 */
	synchronizationDone: () => {
		utils.writeConfig('gamestate.json', JSON.stringify(self.areSynchronied, null, 2), 'sync')
		ipcRenderer.send('synchronization-done')
		ipcRenderer.send('boom')
	}
}
