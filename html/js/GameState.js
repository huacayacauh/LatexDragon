const utils = require('./utils')

class GameState {
	constructor () {
		//Array containing all the informations on the state of the differents games
		this.array = []

		//Index of the current game
		this.currentGame = -1

		//maximum number of gameState
		this.maxGame = 20

		this.inCreation = null
	}

	/**
	 *
	 */
	static initGameState () {
		var data = utils.readConfigSync('gamestate.json')

		var gameState = new GameState()

		if (data == null)
			return gameState

		data = JSON.parse(data)

		for (var i in data) {
			var game = new Game()
			Object.assign(game, data[i])

			gameState.addGame(game)

			gameState.currentGame = i
		}
		return gameState
	}

	isComplete () {
		return this.array[this.currentGame].isComplete()
	}

	addGame (game) {
		if (this.array.length < this.maxGame)
			this.array.push(game)
	}

	getCurrent () {
		return this.array[this.currentGame]
	}

	delete (id) {
		for (var i in this.array) {
			if (this.array[i].gameId == id) {
				this.array.splice(i, 1)
				break
			}
		}
		this.updateCurrent()
	}

	updateCurrent () {
		if (this.array.length == 0)
			this.currentGame = -1
		else
			this.currentGame = 0
	}

	startCreation () {
		this.inCreation = new Game()
	}

	finishCreation () {
		this.addGame(this.inCreation)
		this.inCreation = null
		this.currentGame = this.array.length-1
	}

	stopCountdown () {
		for (var i in this.array) {
			if ((this.array[i].countdown != null) && (this.array[i].countdown.state == 'STARTED'))
				this.array[i].countdown.stopCountdown()
		}
	}

	static getNewGame () {
		return new Game()
	}

	toString () {
		return JSON.stringify(this.array, null, 2)
	}
}

module.exports = GameState

class Game {

	constructor () {
		//Id of the client on the server
		this.gameId = null

		//Id of which formula the game is using
		this.formulaId = null

		//Latex string of the formula
		this.formulaLatex = null

		//Which mode the player is currently playing with
		this.mode = null

		//Which set of rules the player is currently playing with
		this.ruleSet = null

		//Boolean, Do the game use theorem created by users ?
		this.useTheorem = null

		//The countdown of the game, is null expect if the game mode is normal
		this.countdown = null

		//Json object of the last/current game request to the server
		this.currentState = null
	}

	/**
	 * Return true if the object is complete meaning all his fields are filled in.
	 * Return false otherwise.
	 */
	isComplete () {
		if ((this.gameId != null) && (this.formulaId != null) && (this.mode != null) && (this.ruleSet != null) && (this.useTheorem != null))
			return true
		return false
	}
}
