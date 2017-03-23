/**
 * Called when the document finished loading and is ready.
 * Request the game html file (By default the app start on the game "tab")
 */
$(document).ready (function () {
	const utils = require('./js/utils')
	const app = require('./js/Application')

	utils.setWindowsControlEvents()

	utils.initAnimateCss()

	console.log('[CLIENT]: Document ready')
	app.requestHtml('HOME')
});

/**
 * Called before the window close. Save everything (settings, gamestate ...).
 * @listens window~event:onbeforeunload
 */
window.onbeforeunload = () => {
	const utils = require ('./js/utils')
	const app = require('./js/Application')

	utils.writeConfig('settings.json', app.settings, 'sync')
	utils.writeConfig('gamestate.json', app.gameState, 'sync')
}
