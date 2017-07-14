package onlyedu.com.payapp;


import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yangxiaoguang on 2017/7/14.
 */

public class HttpCore {

    private OkHttpClient mOkHttpClient=new OkHttpClient();
    private void postAsynHttp() {

        RequestBody formBody = new FormBody.Builder()
                .add("size", "10")
                .build();
        Request request = new Request.Builder()
                .url("http://api.1-blog.com/biz/bizserver/article/list.do")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
//        Response response = call.execute();
//        response.body().string();
    }
}
