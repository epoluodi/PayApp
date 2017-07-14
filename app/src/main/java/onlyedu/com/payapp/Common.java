package onlyedu.com.payapp;

import android.util.Base64;
import android.util.Log;

/**
 * Created by yangxiaoguang on 2017/7/14.
 */

public class Common {

    /**
     * base解密二维码内容
     * @param code
     * @return
     */
    public static String basedecode(String code) {
        return new String(Base64.decode(code, Base64.DEFAULT));
    }
}
