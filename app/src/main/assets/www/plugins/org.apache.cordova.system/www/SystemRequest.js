//fengxf added begin:
cordova.define("onlyedu.com.payapp.Plugin.SystemPlugin", function(require,exports,module){

    var exec = require('cordova/exec');

    module.exports = {	
		/** 
		 * 一共5个参数 
		   第一个 :成功回调 
		   第二个 :失败回调 
		   第三个 :将要调用的类的配置名字
		   第四个 :调用的方法名(一个类里可能有多个方法 靠这个参数区分) 
		   第五个 :传递的参数  以json的格式 
		 */




        //设置web标题信息
        title: function(jsonparams,getRequestHeaderCallBackSuccess,getRequestHeaderCallBackFail) {
           exec(getRequestHeaderCallBackSuccess, getRequestHeaderCallBackFail, "SystemRequest", "title", [jsonparams]);
        },
        //android toast
        toast: function(jsonparams) {
           exec(null, null, "SystemPlugin", "toast", [jsonparams]);
        },

        //扫描
        scan: function(arg,callback) {
           exec(callback, null, "SystemPlugin", "scan", [arg]);
        },
        //回退
        goback: function() {
           exec(null, null, "SystemPlugin", "goback", [null]);
        },
        closeReturn: function() {
           exec(null, null, "SystemPlugin", "closeReturn", [null]);
        },
    };

});
//fengxf added end


