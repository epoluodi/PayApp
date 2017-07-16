//fengxf added begin:
cordova.define("onlyedu.com.payapp.Plugin.EPTBeeperPlugin", function(require,exports,module){

    var exec = require('cordova/exec');

    module.exports = {	


        //打开蜂鸣器
        startBeep:function(msec) {
           exec(null, null, "EPTBeeperPlugin", "startBeep", [msec]);
        },

        //停止蜂鸣器
        stopBeep:function() {
           exec(null, null, "EPTBeeperPlugin", "stopBeep", [null]);
        },

    };

});



