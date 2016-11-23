/** Class Request, used to send request to the Java rest api */
class Request {
  /**
   * @param {String} serverUrl server url to which the request is send to
   * @param {String} type type of the http request (GET, POST ...)
   * @param {String} dataType type of the response (HTML, JSON, TEXT ...)
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
   * Execute an asynchronous HTTP request to the server and call this.response() when complete.
   * @param {String} [item] string of arguments that need to be sent
   */
  send (item) {
    var self = this;
    var url = this.serverUrl;

    if (item != undefined)
      url = this.serverUrl + item;

    $.ajax({
      url:url,
      type:this.type,
      dataType:this.dataType,
      complete: function (response, status) {
        console.log("[REQUEST]: "+ new Date().toString() + "\nUrl:" + self.serverUrl + "\nRetrurned status : " + response.status + "|" + status);
        self.response(response, status);
      }
    });
  }

  /**
   * Store the response from the request, and if defined call the responseHandler function.
   * @param {Object} response response from the request (jQuery ajax response)
   * @param {String} status status response from the request
   */
  response (response, status) {
    if (this.dataType == "text")
      console.log("[REQUEST]: Message : " + response.responseText);

    this.responseMessage = response;

    if (this.responseHandler != undefined)
      this.responseHandler(response, status);
  }
  /**
   * Static method that create a request using EnumHelper.
   * @param {string} requestName name of the request defined in EnumHelper
   * @param {function} [responseHandler] function called when the request is completed, can be omitted
   * @returns {Request}
   * @static
   */
  static buildRequest (requestName, responseHandler) {
      return new Request (EnumHelper.REQUESTS[requestName].url, EnumHelper.REQUESTS[requestName].type, EnumHelper.REQUESTS[requestName].dataType, responseHandler);
  }
}
