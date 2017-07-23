package onlyedu.com.payapp.Plugin;

import android.content.Intent;
import android.os.Message;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import onlyedu.com.payapp.WebActivity.WebActivity;

/**
 * app 系统插件
 * @author yxg
 *
 */
public class SystemPlugin extends CordovaPlugin {
	
	private static final String TAG = "SystemPlugin";







    @Override
    public boolean execute(String action, JSONArray args,
    		CallbackContext callbackContext) throws JSONException {
    	this.callbackContext = callbackContext;
		Message message=new Message();
		//web标题
		if (action.equals("title")) {
			message.obj=args.getJSONObject(0);
			this.cordova.onMessage("title", message);
			return true;
		}



		//账号信息
		if (action.equals("toast")) {
			try
			{
				String desc = args.getString(0);
				Toast.makeText(cordova.getActivity(),desc,Toast.LENGTH_SHORT).show();

			}
			catch (Exception e)
			{e.printStackTrace();}
			return true;
		}


		if (action.equals("scan")) {
			try
			{
				jsondata = args.getJSONObject(0);
				this.cordova.onMessage("scan", this);
			}
			catch (Exception e)
			{e.printStackTrace();}
			return true;
		}
		if (action.equals("goback")) {
			try
			{
				webView.goBack();
			}
			catch (Exception e)
			{e.printStackTrace();}
			return true;
		}
    	return true;
    }
}
