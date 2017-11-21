package onlyedu.com.payapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yangxiaoguang on 2017/10/5.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //创建需要启动的Activity
        Intent intent1=new Intent(context,MainActivity.class);
        //设置参数必须
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //启动
        context.startActivity(intent1);
    }
}
