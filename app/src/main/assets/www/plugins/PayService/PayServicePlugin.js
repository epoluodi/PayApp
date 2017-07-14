//fengxf added begin:
cordova.define("onlyedu.com.payapp.Plugin.PayServicePlugin", function(require,exports,module){

    var exec = require('cordova/exec');

    module.exports = {	

        //签到
        sign: function() {
           exec(null, null, "PayServicePlugin", "sign", [null]);
        },

        //消费
        consume:function(amount,callback) {
           exec(callback, null, "PayServicePlugin", "consume", [amount]);
        },

        //消费
        cancelConsume:function(oldTrace,callback) {
           exec(callback, null, "PayServicePlugin", "cancelConsume", [oldTrace]);
        },

        //退货
        returnGoods:function(amount,callback) {
           exec(callback, null, "PayServicePlugin", "returnGoods", [amount]);
        },

        //余额查询
        balanceInquiry:function() {
           exec(null, null, "PayServicePlugin", "balanceInquiry", [null]);
        },

        //结算
        settlement:function() {
           exec(null, null, "PayServicePlugin", "settlement", [null]);
        },

        //系统管理
        systemManage:function() {
           exec(null, null, "PayServicePlugin", "systemManage", [null]);
        },

        //打印
        print:function() {
           exec(null, null, "PayServicePlugin", "print", [null]);
        },

        //初始化打印
        initPrint:function() {
           exec(null, null, "PayServicePlugin", "initPrint", [null]);
        },
    };

});



