package onlyedu.com.payapp.WebActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import onlyedu.com.payapp.R;

public class WebActivity extends AppCompatActivity implements CordovaInterface {

    private CordovaWebView cordovaWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        cordovaWebView = (CordovaWebView)findViewById(R.id.cordovaWebView);

        cordovaWebView.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    public void startActivityForResult(CordovaPlugin command, Intent intent, int requestCode) {

    }

    @Override
    public void setActivityResultCallback(CordovaPlugin plugin) {

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Object onMessage(String id, Object data) {
        return null;
    }

    private final ExecutorService threadPool = Executors.newCachedThreadPool();
    @Override
    public ExecutorService getThreadPool() {
        return threadPool;
    }

    @Override
    protected void onResume() {
        super.onResume();

        cordovaWebView.onResume();
        cordovaWebView.handleResume(true, true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        cordovaWebView.onPause();
        cordovaWebView.handlePause(true);
    }

    @Override
    protected void onStop() {
        cordovaWebView.handleDestroy();
        super.onStop();
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode ==4)
        {
            if (cordovaWebView.canGoBack())
            {
                cordovaWebView.goBack();
                return false;
            }
            else {
                finish();
                return false;
            }
        }
        return super.onKeyUp(keyCode, event);

    }
}
