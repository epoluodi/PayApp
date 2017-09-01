//fengxf added begin:
cordova.define("onlyedu.com.payapp.Plugin.EPTPrintPlugin", function(require,exports,module){

    var exec = require('cordova/exec');

    module.exports = {	

        //进纸
        feedLine: function(line) {
           exec(null, null, "EPTPrintPlugin", "feedLine", [line]);
        },

        //微信，支付宝打印
        printNet:function(arg,callback) {
           exec(callback, null, "EPTPrintPlugin", "printNet", [arg]);
        },

        //现金打印
        printCash:function(arg,callback) {
           exec(callback, null, "EPTPrintPlugin", "printCash", [arg]);
        },

       //网银转账打印
        printTrans:function(arg,callback) {
           exec(callback, null, "EPTPrintPlugin", "printTrans", [arg]);
        },
    };

});



