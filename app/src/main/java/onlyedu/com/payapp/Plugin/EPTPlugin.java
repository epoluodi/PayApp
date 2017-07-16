package onlyedu.com.payapp.Plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.device.Beeper;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by yangxiaoguang on 2017/7/15.
 */

public class EPTPlugin extends CordovaPlugin {
    private static final String TAG = "EPTPlugin";

    private CallbackContext callbackContext = null;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {



        //签到
        if(action.equals("login"))
        {
            try {
                DeviceService.login(cordova.getActivity().getApplicationContext());
                Toast.makeText(cordova.getActivity(),"设备登录成功",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                Toast.makeText(cordova.getActivity(),"设备登录失败",Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        //登出
        if(action.equals("logout"))
        {
            DeviceService.logout();
            Toast.makeText(cordova.getActivity(),"设备登出成功",Toast.LENGTH_SHORT).show();
            return true;
        }




        return super.execute(action, args, callbackContext);
    }
}
