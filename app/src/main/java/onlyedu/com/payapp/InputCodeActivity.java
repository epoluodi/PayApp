package onlyedu.com.payapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import onlyedu.com.payapp.WebActivity.WebActivity;

public class InputCodeActivity extends AppCompatActivity {

    private Button btn1,btn2;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_code);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        input = (EditText)findViewById(R.id.input);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpUrl.codeUrl = String.format(HttpUrl.codeUrlModel,HttpUrl.host,input.getText().toString());
                Intent intent=new Intent(InputCodeActivity.this,WebActivity.class);
                intent.putExtra("url",HttpUrl.codeUrl);
                startActivity(intent);
            }
        });

    }
}
