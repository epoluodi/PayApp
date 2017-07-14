package onlyedu.com.payapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import onlyedu.com.payapp.Scan.ScanActivity;
import onlyedu.com.payapp.Scan.decoding.Intents;
import onlyedu.com.payapp.WebActivity.WebActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnscan,btnopenweb,btncodedo;
    private TextView code,codeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code=(TextView)findViewById(R.id.code);
        codeResult = (TextView)findViewById(R.id.codeResult);
        btnopenweb = (Button)findViewById(R.id.btnopenweb);
        btncodedo = (Button)findViewById(R.id.btncodedo);

        btnscan=(Button)findViewById(R.id.btnscan);
        btnscan.setOnClickListener(onClickListenerbtnsacan);

        btnopenweb.setOnClickListener(onClickListenerOpenWeb);
        btncodedo.setOnClickListener(onClickListenercodedo);
    }

    View.OnClickListener onClickListenerbtnsacan = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this, ScanActivity.class);
            startActivityForResult(intent,ScanActivity.SCANRESULTREQUEST);
        }
    };


    View.OnClickListener onClickListenerOpenWeb = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(MainActivity.this, WebActivity.class);
            startActivity(intent);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
        }
    };
}
