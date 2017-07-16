//fengxf added begin:
cordova.define("onlyedu.com.payapp.Plugin.EPTPlugin", function(require,exports,module){

    var exec = require('cordova/exec');

    module.exports = {	

        //签到
        login: function() {
           exec(null, null, "EPTPlugin", "login", [null]);
        },

        //注销
        logout:function() {
           exec(null, null, "EPTPlugin", "logout", [null]);
        },

    };

});



