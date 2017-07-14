cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/PayService/PayServicePlugin.js",
        "id": "onlyedu.com.payapp.Plugin.PayServicePlugin",
        "merges": [
        "PayService"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.system/www/SystemRequest.js",
        "id": "onlyedu.com.payapp.Plugin.SystemPlugin",
        "merges": [
        "System"
        ]
    },



];
module.exports.metadata = 
// TOP OF METADATA
{
    "com.suypower.stereo.suehome.CordovaPlugin.http.FavoriteRequest": "0.0.1",


}
// BOTTOM OF METADATA
});