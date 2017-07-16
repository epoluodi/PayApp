package onlyedu.com.payapp.Plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * 支付接口
 * 一共9个.
 */

public class PayServicePlugin extends CordovaPlugin {
    private static final String TAG = "PayServicePlugin";

    private CallbackContext callbackContext = null;

    @Override
    public boolean execute(String action, JSONArray args,
                           CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        Message message=new Message();

        //签到
        if(action.equals("sign"))
        {
            Log.i("支付接口","签到");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "签到");
            cordova.getActivity().startActivityForResult(intent, 0);
            return true;
        }


        //消费
        if(action.equals("consume"))
        {
            Log.i("支付接口","消费");
            String amount = args.getString(0);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "消费");
            intent.putExtra("amount", amount);
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }

        //消费撤销
        if(action.equals("cancelConsume"))
        {
            Log.i("支付接口","消费撤销");
            String oldTrace = args.getString(0);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "消费撤销");
            intent.putExtra("oldTrace", oldTrace);
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }


        //退货
        if(action.equals("returnGoods"))
        {
            Log.i("支付接口","退货");
            String amount = args.getString(0);
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "退货");
            intent.putExtra("amount", amount);
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }


        //余额查询
        if(action.equals("balanceInquiry"))
        {
            Log.i("支付接口","余额查询");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "余额查询");
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }

        //结算
        if(action.equals("settlement"))
        {
            Log.i("支付接口","结算");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "结算");
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }

        //系统管理
        if(action.equals("systemManage"))
        {
            Log.i("支付接口","系统管理");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "系统管理");
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }

        //打印
        if(action.equals("print"))
        {
            Log.i("支付接口","打印");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "打印");
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }

        //初始化打印
        if(action.equals("initPrint"))
        {
            Log.i("支付接口","初始化打印机");
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.landicorp.android.unionpay",
                    "com.landicorp.android.unionpay.TestActivity"));
            intent.putExtra("transName", "初始化打印机");
            cordova.getActivity().startActivityForResult(intent, 0);

            return true;
        }
        return true;
    }



}
