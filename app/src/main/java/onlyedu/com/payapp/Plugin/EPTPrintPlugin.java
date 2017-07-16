package onlyedu.com.payapp.Plugin;

import android.widget.Toast;

import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.device.Printer;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by yangxiaoguang on 2017/7/15.
 */

public class EPTPrintPlugin extends CordovaPlugin {
    private static final String TAG = "EPTPrintPlugin";



    private CallbackContext callbackContext = null;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {



        //签到
        if(action.equals("feedLine"))
        {
            final int line = args.getInt(0);
            try {

                Printer.Progress progress=new Printer.Progress() {
                    @Override
                    public void doPrint(Printer printer) throws Exception {
                        Printer.getInstance().feedLine(line);
                    }

                    @Override
                    public void onFinish(int i) {

                    }

                    @Override
                    public void onCrash() {

                    }
                };
                progress.start();

            }catch (Exception e)
            {e.printStackTrace();
                Toast.makeText(cordova.getActivity(),"请先登录登录，错误信息:" +e.getMessage(),Toast.LENGTH_SHORT).show();
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
