/**
 * Main process script.
 * Some event handler are not showing in the documentation refer to the source code.
 * Not really a module, only for documentation purpose.
 * @module main
 */

const {app, BrowserWindow, ipcMain, shell} = require('electron')

/**
 * Global reference to the windows
 */
global.windowsArray = {
  "app" : null,
  "doc" : null
}

/**
 * Global reference to all the background process
 */
global.backgroundProcess = []

/**
 * Function creating the main window of the app.
 * The window is created at the launch of the app.
 * Closing this window will quit the app.
 * The window created will be maximized to fit the screen and load index.html.
 */
function createMainWindow () {
  //Create the browser window
  global.windowsArray.app = new BrowserWindow({width: 900, height: 600, frame: false, minWidth: 900, minHeight: 600})

  //Maximize the window
  global.windowsArray.app.maximize()

  //Remove the default menubar
  global.windowsArray.app.setMenu(null)

  //Load the index.html of the app
  global.windowsArray.app.loadURL(`file://${__dirname}/index.html`)

  //Open the DevTools
  //global.windowsArray.app.webContents.openDevTools({mode: 'bottom'})

  //When the window is closed the app quit
  global.windowsArray.app.on('closed', () => {
    app.quit()
  })
}

/**
 * Create a new window and use it to display the documentation.
 * Will focus on the doc window if already open.
 */
function createDocWindow () {
  if (global.windowsArray.doc == null) {
    global.windowsArray.doc = new BrowserWindow({width: 1280, height: 640})

    global.windowsArray.doc.setMenu(null)

    global.windowsArray.doc.loadURL(`file://${__dirname}/out/index.html`)

    global.windowsArray.doc.on('closed', () => {
      global.windowsArray.doc = null
    })
  }
  else
    global.windowsArray.doc.focus()
}

/**
 * Will create an invisible window and execute the task given as an argument
 * in the window.
 * @param {String} task javascript code to execute in the background process
 */
function createNewBackgroundProcess (task) {
	var bgProcess = new BrowserWindow ({show:false})
	//var bgProcess = new BrowserWindow ()

	bgProcess.loadURL(`file://${__dirname}/backgroundProcess.html`)

	//bgProcess.webContents.openDevTools()

	bgProcess.webContents.executeJavaScript(task)

	global.backgroundProcess.push(bgProcess)
}

/**
 * Handler of the display-doc event.
 * Call createDocWindow when received.
 * Used by the renderer process to communicate with the main process.
 * @listens event:display-doc
 * @method
 */
ipcMain.on('display-doc', (event, arg) => {
  createDocWindow()
})

/**
 * Handler of the server-status event.
 * Send a message to the app renderer to inform it of the server status.
 * @method
 * @listens event:server-status
 */
ipcMain.on('server-status', (event, arg) => {
  global.windowsArray.app.webContents.send('server-status', arg)
})

/**
 * Handler of the synchronize-done event.
 * Send a message to the app renderer to inform it that the synchronization with the
 * server is done.
 * @method
 * @listens event:synchronization-done
 */
ipcMain.on('synchronization-done', (event, arg) => {
  global.windowsArray.app.webContents.send('synchronization-done', arg)
})

/**
 * Handler of the new-background-process event.
 * Create a new process and execute the task given as argument.
 * @method
 * @listens event:new-background-process
 */
ipcMain.on('new-background-process', (event, arg) => {
	createNewBackgroundProcess(arg)
})

/**
 * Handler of the close-background-process event.
 * Close all background process.
 * @method
 * @listens event:close-background-process
 */
ipcMain.on('close-background-process', (event, arg) => {
	for (var i = global.backgroundProcess.length-1 ; i >= 0 ; i--) {
		global.backgroundProcess[i].destroy()
		global.backgroundProcess.pop()
	}
})

/**
 * Handler of the boom event.
 * Destroy the renderer process that sent the event.
 * @method
 * @listens event:boom
 */
ipcMain.on('boom', (event, arg) => {
	var win = BrowserWindow.fromWebContents(event.sender)
	win.destroy()
})

/**
 * Handler of the boom event.
 * Open an url using the user default browser.
 * @method
 * @listens event:open-link-in-browser
 */
ipcMain.on('open-link-in-browser', (event, arg) => {
	shell.openExternal(arg)
})

/**
 * This method will be called when Electron has finished initialization and is ready to create a browser windows.
 * When the app is ready will create the main window.
 * @method
 * @listens event:ready
 */
app.on('ready', createMainWindow)

/**
 * When all windows are closed the app quit.
 * @method
 * @listens event:window-all-closed
 */
app.on('window-all-closed', () => {
	app.quit()
})
