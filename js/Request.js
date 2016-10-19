/** Class Request, used to send request to the Java rest api */
class Request {
  /**
   * @constructor
   * @param {string} sesrverUrl server url to which the request is send to
   * @param {string} type type of the http request (GET, POST ...)
   * @param {string} dataType type of the response (HTML, JSON, TEXT ...)
   * @param {function} [responseHandler] function called when the request is completed, can be omitted
   */
  constructor (serverUrl, type, dataType, responseHandler) {
    this.serverUrl = serverUrl;
    this.type = type;
    this.dataType = dataType;
    if (responseHandler == undefined)
      this.responseHandler = undefined;
    else
      this.responseHandler = responseHandler;
    this.responseMessage = undefined;
  }

  /**
   * Execute an asynchronous HTTP request to the server and call this.response() when complete
   */
  send () {
    var self = this;
    $.ajax({
      url:this.serverUrl,
      type:this.type,
      dataType:this.dataType,
      complete: function (response, status) {
        console.log("[REQUEST]: "+ new Date().toString() + "\nUrl:" + self.serverUrl + "\nRetrurned status : " + response.status + "|" + status);
        self.response(response);
      }
    });
  }

  /**
   * store the response from the request, and if defined call the responseHandler function
   * @param {Object} response response from the request (jQuery ajax response)
   */
  response (response) {
    if (this.dataType == "text")
      console.log("[REQUEST]: Message : " + response.responseText);
    this.responseMessage = response;
    if (this.responseHandler != undefined)
      this.responseHandler(response);
  }
  /**
   * Static method that create a request using EnumHelper
   * @param {string} requestName name of the request defined in EnumHelper
   * @param {function} [responseHandler] function called when the request is completed, can be omitted
   * @returns {Object}
   */
  static buildRequest (requestName, responseHandler) {
      return new Request (EnumHelper.REQUESTS[requestName].url, EnumHelper.REQUESTS[requestName].type, EnumHelper.REQUESTS[requestName].dataType, responseHandler);
  }
}
