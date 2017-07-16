package onlyedu.com.payapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.landicorp.android.eptapi.DeviceService;


import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import onlyedu.com.payapp.Scan.ScanActivity;
import onlyedu.com.payapp.WebActivity.WebActivity;

public class TestActivity extends AppCompatActivity {

    private Button btnscan,btnopenweb,btncodedo;
    private TextView code,codeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        code=(TextView)findViewById(R.id.code);
        codeResult = (TextView)findViewById(R.id.codeResult);
        btnopenweb = (Button)findViewById(R.id.btnopenweb);
        btncodedo = (Button)findViewById(R.id.btncodedo);

        btnscan=(Button)findViewById(R.id.btnscan);
        btnscan.setOnClickListener(onClickListenerbtnsacan);

        btnopenweb.setOnClickListener(onClickListenerOpenWeb);
        btncodedo.setOnClickListener(onClickListenercodedo);

//        //先登出支付服务
//        DeviceService.logout();
    }

    View.OnClickListener onClickListenerbtnsacan = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(TestActivity.this, ScanActivity.class);
            startActivityForResult(intent,ScanActivity.SCANRESULTREQUEST);

        }
    };


    View.OnClickListener onClickListenerOpenWeb = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(TestActivity.this, WebActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("onActivityResult 回调 " ,String.valueOf(requestCode));
        if (requestCode ==ScanActivity.SCANRESULTREQUEST)
        {
            if (resultCode ==1)
            {
                code.setText("扫描结果:" + data.getExtras().getString("code"));
            }
        }
    }

    View.OnClickListener onClickListenercodedo =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            codeResult.setText(Common.basedecode(code.getText().toString()));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    requestPostBySynWithForm("",codeResult.getText().toString());
                }
            }).start();

        }
    };


    private void requestPostBySynWithForm(String actionUrl, String params) {
        try {

            final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=UTF-8");
            final String BASE_URL = "http://192.168.1.152";//请求接口根地址
            //创建一个FormBody.Builder

            OkHttpClient okHttpClient=new OkHttpClient();
            RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, params);

            //补全请求地址
            String requestUrl = String.format("%s/%s", BASE_URL, "api/app/payinfo");
            //创建一个请求
            Request.Builder builder = new Request.Builder();
            final Request request = builder.url(requestUrl).post(body).build();
            //创建一个Call





            final Call call = okHttpClient.newCall(request);
            //执行请求

            Response response = call.execute();
            if (response.isSuccessful()) {
                Log.e("", "response ----->" + response.body().string());
            }
        } catch (Exception e) {
            Log.e("", e.toString());
        }
    }


}
