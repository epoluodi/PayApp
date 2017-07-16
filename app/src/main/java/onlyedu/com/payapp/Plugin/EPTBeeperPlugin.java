package onlyedu.com.payapp.Plugin;

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

public class EPTBeeperPlugin extends CordovaPlugin {
    private static final String TAG = "EPTBeeperPlugin";

    private CallbackContext callbackContext = null;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {





        //打开蜂鸣器
        if(action.equals("startBeep"))
        {
            int msec = args.getInt(0);

            Beeper.startBeep(msec);

            return true;
        }

        //停止蜂鸣器
        if(action.equals("stopBeep"))
        {
            Beeper.stopBeep();
            return true;
        }


        return super.execute(action, args, callbackContext);
    }
}
