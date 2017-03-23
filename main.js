/**
 * Main process script.
 * Some event handler are not showing in the documentation refer to the source code.
 * @module main
 */

const {app, BrowserWindow, ipcMain} = require('electron')

/**
 * Global reference to the windows
 * @global
 */
global.windowsArray = {
  "app" : null,
  "doc" : null
}

/**
 * Function creating the main window of the app.
 * The window is created at the launch of the app.
 * Closing this window will quit the app.
 * The window created will be maximized to fit the screen and load index.html.
 */
function createMainWindow () {
  //Create the browser window
  global.windowsArray.app = new BrowserWindow({width: 800, height: 600, frame: false, minWidth: 800, minHeight: 600})

  //Maximize the window
  global.windowsArray.app.maximize()

  //Remove the default menubar
  global.windowsArray.app.setMenu(null)

  //Load the index.html of the app
  global.windowsArray.app.loadURL(`file://${__dirname}/index.html`)

  //Open the DevTools
  global.windowsArray.app.webContents.openDevTools({mode: 'bottom'})

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
 * Handler of the display-doc event.
 * Call createDocWindow when received.
 * Used by the renderer process to communicate with the main process.
 * @listens display-doc
 */
ipcMain.on('display-doc', (event, arg) => {
  createDocWindow()
})

/**
 * This method will be called when Electron has finished initialization and is ready to create browser windows.
 * When the app is ready will create the main window.
 * @listens ready
 */
app.on('ready', createMainWindow)

/**
 * When all windows are closed the app quit.
 * @listens window-all-closed
 */
app.on('window-all-closed', () => {
	app.quit()
})
