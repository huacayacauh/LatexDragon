/**
 * @global
 * @constant
 * Global object regourping all enumerations (contain only 1 enum currently)
 */
const EnumHelper = {
  REQUESTS:{
    "GAMESTATE":{
      "url":"http://localhost:8080/LibreDragon/api/test",
      "type":"GET",
      "dataType":"text"
    },
    "GAME":{
      "url":"./html/game.html",
      "type":"GET",
      "dataType":"html"
    },
    "SETTINGS":{
      "url":"./html/settings.html",
      "type":"GET",
      "dataType":"html"
    },
    "HELP":{
      "url":"./html/help.html",
      "type":"GET",
      "dataType":"html"
    },
    "APPLYRULE":{
      "url":"http://localhost:8080/LibreDragon/api/rule",
      "type":"GET",
      "dataType":"text"
    },
    "START":{
      "url":"http://localhost:8080/LibreDragon/api/start",
      "type":"GET",
      "dataType":"text"
    },
    "OVER":{
      "url":"http://localhost:8080/LibreDragon/api/over",
      "type":"GET",
      "dataType":"text"
    }
  }
}
