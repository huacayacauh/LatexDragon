const utils = require ('./js/utils')
const app = require('./js/Application')
const GameState = require('./js/GameState')
const {ipcRenderer} = require('electron')

/**
 * Called when the document finished loading and is ready.
 * Request the game html file (By default the app start on the game "tab")
 */
$(document).ready (function () {
	utils.setWindowsControlEvents()

	utils.initAnimateCss()

	console.log('[CLIENT]: Starting server status checker...')
	ipcRenderer.send('new-background-process', 'require("./js/serverStatus").serverStatusRequest()')

	console.log('[CLIENT]: Synchronizing...')
	ipcRenderer.send('new-background-process', 'require("./js/synchronize").synchronize()')

	if (app.settings.devmode)
		$('#devmode').show()

	console.log('[CLIENT]: Document ready')
	app.requestHtml('HOME')
})

/**
 * Called before the window close. Save everything (settings, gamestate ...).
 * @listens window~event:onbeforeunload
 */
window.onbeforeunload = () => {
	utils.writeConfig('settings.json', app.settings, 'sync')
	utils.writeConfig('gamestate.json', app.gameState, 'sync')
	ipcRenderer.send('close-background-process')
}

ipcRenderer.on('server-status', (event, arg) => {
	app.serverStatus = arg
	if (arg) {
		$('#serverStatus').removeClass('text-danger')
		$('#serverStatus').addClass('text-success')
		$('#serverStatus').attr('data-original-title', 'Serveur en ligne')
	}
	else {
		$('#serverStatus').removeClass('text-success')
		$('#serverStatus').addClass('text-danger')
		$('#serverStatus').attr('data-original-title', 'Serveur hors ligne')
	}
})

ipcRenderer.on('synchronization-done', (event, arg) => {
	app.gameState = GameState.initGameState()

	if (app.currentTab == 'GAME')
		app.loadGameHandler()
})
