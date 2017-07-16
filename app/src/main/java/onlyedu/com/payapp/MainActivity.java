package onlyedu.com.payapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import onlyedu.com.payapp.Scan.ScanActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);


        btn1.setOnClickListener(onClickListenerbtn1);
        btn2.setOnClickListener(onClickListenerbtn2);

    }


    private void installAPK(Uri apk) {

        // 通过Intent安装APK文件
        if (apk ==null)
        {

            Toast.makeText(MainActivity.this,"更新失败，请重新尝试",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intents = new Intent();
        intents.setAction("android.intent.action.VIEW");
        intents.addCategory("android.intent.category.DEFAULT");
        intents.setType("application/vnd.android.package-archive");
        intents.setData(apk);
        intents.setDataAndType(apk, "application/vnd.android.package-archive");
        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intents);
        finish();


    }

    /**
     * 扫码收款
     */
    View.OnClickListener onClickListenerbtn1 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this, ScanActivity.class);
            startActivityForResult(intent,ScanActivity.SCANRESULTREQUEST);

        }
    };

    /**
     * 扫码签到
     */
    View.OnClickListener onClickListenerbtn2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Toast.makeText(MainActivity.this,"暂时没有开放",Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==ScanActivity.SCANRESULTREQUEST)
        {
            if (resultCode ==1)
            {
                String code =data.getExtras().getString("code");
                Log.i("条码:" ,code);
            }
        }
    }

}
