class GameState {
	constructor () {
		//Id of the client on the server
		this.gameId = null

		//Id of which formula the game is using
		this.formulaId = null

		//Which mode the player is currently playing with
		this.mode = null

		//Which set of rules the player is currently playing with
		this.ruleSet = null

		//Boolena, Do the game use theorem created by users ?
		this.useTheorem = null

		//Json object of the last/current game request to the server
		this.currentState = null
	}

	static initGameState () {
		const utils = require('./utils')

		var data = utils.readConfigSync('gamestate.json')

		var gameState = new GameState()

		if (data == null)
			return gameState

		data = JSON.parse(data)

		return Object.assign(gameState, data)
	}

	toString () {
		return JSON.stringify(this, null, 2)
	}
}

module.exports = GameState
