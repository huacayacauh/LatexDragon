/**
 * Request object, used to send request to the Java rest api
 * @this{Request}
 */
class Request {
  /**
   * @constructor
   * @this{Request}
   * @param{serverUrl} server url to which the request is send to
   * @param{type} type of the http request (GET, POST ...)
   * @param{dataType} type of the response (HTML, JSON, TEXT ...)
   * @param{responseHandler} function called when the request is completed, can be omitted
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
        console.log("[Request] "+ new Date().toString() + "\nUrl:" + self.serverUrl + "\nRetrurned status : " +  status);
        self.response(response);
      }
    });
  }

  /**
   * store the response from the request, and if defined call the responseHandler function
   * @param{response} response from the request
   */
  response (response) {
    this.responseMessage = response;
    if (this.responseHandler != undefined)
      this.responseHandler(response);
  }
}
