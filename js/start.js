const utils = require ('./js/utils')
const app = require('./js/Application')
const GameState = require('./js/GameState')
const {ipcRenderer} = require('electron')

/**
 * Called when the document finished loading and is ready.
 * Initialize the window control buttons, the animateCss function, create the
 * background process for the server status and synchronize the client with
 * the server.
 * Request the game html file (By default the app start on the 'HOME' tab)
 */
$(document).ready (function () {
	utils.setWindowControlEvents()

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
 * And close all	the background process.
 * @listens window~event:onbeforeunload
 */
window.onbeforeunload = () => {
	utils.writeConfig('settings.json', app.settings, 'sync')
	utils.writeConfig('gamestate.json', app.gameState, 'sync')
	ipcRenderer.send('close-background-process')
}

/**
 * Event received when the status of the server change. Update the server status
 * variable in the app object and if the server was offline will synchronize
 * automatically with the server. Also if the user was in the 'GAME' tab the tab
 * will be reloaded.
 * @listens module:main~event:server-status
 */
ipcRenderer.on('server-status', (event, arg) => {
	app.serverStatus = arg
	if (arg) {
		$('#serverStatus').removeClass('text-danger')
		$('#serverStatus').addClass('text-success')
		$('#serverStatus').attr('data-original-title', 'Serveur en ligne')
		if (app.gameState != null)
			app.synchronize()
		if (app.currentTab == 'GAME')
			app.requestHtml('GAME')
	}
	else {
		$('#serverStatus').removeClass('text-success')
		$('#serverStatus').addClass('text-danger')
		$('#serverStatus').attr('data-original-title', 'Serveur hors ligne')
		if (app.currentTab == 'GAME')
			app.requestHtml('GAME')
	}
})

/**
 * Event received when the synchronization is done, create the GameState object
 * and if the user was on the game tab the tab is reloaded.
 * @listens module:main~event:synchronization-done
 */
ipcRenderer.on('synchronization-done', (event, arg) => {
	app.gameState = GameState.initGameState()

	if (app.currentTab == 'GAME')
		app.requestHtml('GAME')
})
