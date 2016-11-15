const {app, BrowserWindow, ipcMain} = require('electron')

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
global.windowsArray = {
  "app" : null,
  "doc" : null
}

function createMainWindow () {
  // Create the browser window.
  global.windowsArray.app = new BrowserWindow({width: 1280, height: 640})

  //  Remove the default menubar
  global.windowsArray.app.setMenu(null);

  // load the index.html of the app.
  global.windowsArray.app.loadURL(`file://${__dirname}/index.html`)

  // Open the DevTools.
  global.windowsArray.app.webContents.openDevTools()

  // Emitted when the window is closed.
  global.windowsArray.app.on('closed', () => {
    app.quit();
  })
}

/**
 * Create a new window and use it to display the documentation.
 * Will focus on the doc window if already open.
 */
function createDocWindow () {
  if (global.windowsArray.doc == null) {
    global.windowsArray.doc = new BrowserWindow({width: 1280, height: 640})

    global.windowsArray.doc.setMenu(null);

    global.windowsArray.doc.loadURL(`file://${__dirname}/out/index.html`)

    global.windowsArray.doc.on('closed', () => {
      global.windowsArray.doc = null;
    })
  }
  else
    global.windowsArray.doc.focus();
}

ipcMain.on('display-doc', (event, arg) => {
  createDocWindow()
})

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', createMainWindow)

// Quit when all windows are closed.
app.on('window-all-closed', () => {
  // On macOS it is common for applications and their menu bar
  // to stay active until the user quits explicitly with Cmd + Q
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  // On macOS it's common to re-create a window in the app when the
  // dock icon is clicked and there are no other windows open.
  if (win === null) {
    createWindow()
  }
})

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.
