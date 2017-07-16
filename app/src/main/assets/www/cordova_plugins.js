cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/PayService/PayServicePlugin.js",
        "id": "onlyedu.com.payapp.Plugin.PayServicePlugin",
        "merges": [
        "payService"
        ]
    },
    {
        "file": "plugins/org.apache.cordova.system/www/SystemRequest.js",
        "id": "onlyedu.com.payapp.Plugin.SystemPlugin",
        "merges": [
        "system"
        ]
    },
    {
        "file": "plugins/Ept/EptPlugin.js",
        "id": "onlyedu.com.payapp.Plugin.EPTPlugin",
        "merges": [
        "ept"
        ]
    },
    {
        "file": "plugins/Ept/EptBeeperPlugin.js",
        "id": "onlyedu.com.payapp.Plugin.EPTBeeperPlugin",
        "merges": [
        "ept.beeper"
        ]
    },
    {
        "file": "plugins/Ept/EptPrintPlugin.js",
        "id": "onlyedu.com.payapp.Plugin.EPTPrintPlugin",
        "merges": [
        "ept.print"
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