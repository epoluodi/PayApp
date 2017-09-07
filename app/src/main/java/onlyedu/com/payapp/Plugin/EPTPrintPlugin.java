package onlyedu.com.payapp.Plugin;

import android.util.Log;
import android.widget.Toast;

import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.utils.QrCode;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yangxiaoguang on 2017/7/15.
 */

public class EPTPrintPlugin extends CordovaPlugin {
    private static final String TAG = "EPTPrintPlugin";


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {


        this.callbackContext = callbackContext;
        //签到
        if (action.equals("feedLine")) {
            final int line = args.getInt(0);
            try {

                Printer.Progress progress = new Printer.Progress() {
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

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(cordova.getActivity(), "请先登录登录，错误信息:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return true;
        }


        //签到
        if (action.equals("printTick")) {

            JSONObject jsonObject = args.getJSONObject(0);
            try {
                DeviceService.login(cordova.getActivity().getApplicationContext());
                Printer.Progress progress = new Printer.Progress() {
                    @Override
                    public void doPrint(Printer printer) throws Exception {
                        Printer.Format format = new Printer.Format();
                        // Use this 5x7 dot and 1 times width, 2 times height
                        format.setAscSize(Printer.Format.ASC_DOT5x7);
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        printer.setFormat(format);
                        printer.printText("          Landi Pay\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        printer.setFormat(format);
//			printer.printImage(0, "/tmp/1.bmp");
                        printer.printText("--Public utility bill payment receipt--\n");
                        printer.printText("\n");
                        printer.printText("Transaction : Repayment\n");
                        printer.printText("Credit Card No.: XXXX XXXX XXXX XXXX\n");
                        printer.printText("Term No.: 2200306\n");
                        printer.printText("Amount: RMB 100.00\n");
                        printer.printText("Reference No.: 191017234668\n");
                        printer.printText("\n");
                        printer.printText("---The Client Stub---\n");
                        // CHS Text Format - 16x16 dot and 1 times width, 1 times height
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        format.setHzSize(Printer.Format.HZ_DOT16x16);
                        printer.printText("---���������豸���޹�˾---\n");
                        printer.printText("\n");

                        printer.printBarCode("8799128883");

                        printer.printQrCode(0, new QrCode("sdafsadf", QrCode.ECLEVEL_Q), 100);
                        printer.printQrCode(Printer.Alignment.CENTER, new QrCode("landi", QrCode.ECLEVEL_Q), 124);
                        printer.printQrCode(Printer.Alignment.RIGHT, new QrCode("landi", QrCode.ECLEVEL_Q), 124);
                        printer.printText(Printer.Alignment.CENTER, "------landicorp------\n");
                        printer.printText(Printer.Alignment.RIGHT, "www.landicorp.com\n");

                        printer.feedLine(3);
                    }

                    @Override
                    public void onFinish(int i) {
                        if (i == Printer.ERROR_NONE) {
                            EPTPrintPlugin.this.callbackContext.success(1);
                        }
                        /**
                         * Has some error. Here is display it, but you may want to hanle
                         * the error such as ERROR_OVERHEAT��ERROR_BUSY��ERROR_PAPERENDED
                         * to start again in the right time later.
                         */
                        else {
                            EPTPrintPlugin.this.callbackContext.success("PRINT ERR - " + getErrorDescription(i));
                        }
                    }

                    @Override
                    public void onCrash() {
                        EPTPrintPlugin.this.callbackContext.success("-1");
                    }
                };
                progress.start();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(cordova.getActivity(), "请先登录登录，错误信息:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return true;
        }


        //现金收款
        if (action.equals("printCash")) {
//            商户存根(MERCHANT COPY)/客户存根(CUSTOMER COPY)
//            商户名(MERCHANT NAME)：上海昂立智立方教育培训有限公司
//            商户编号(MERCHANT NO)：102310082990540
//            操作员号(OPERATOR NO)：01
//            交易类型(TRANS TYPE)：现金(CASH)
//            日期/时间(DATA/TIME)：2017/07/08 18:14:32
//            交易金额(AMOUNT)：RMB 100.00
//            合同编号(CONTRACT NO)：XFSSH01201708060004
//            备注(REFERENCE)：
//            客户签名(CUSTOMER SIGNATURE)：
            final JSONObject jsonObject = args.getJSONObject(0);
            try {
                DeviceService.login(cordova.getActivity().getApplicationContext());
                Printer.Progress progress = new Printer.Progress() {
                    @Override
                    public void doPrint(Printer printer) throws Exception {
                        printer.setGray(8);
                        Printer.Format format = new Printer.Format();
                        // Use this 5x7 dot and 1 times width, 2 times height
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printMid("现金POS签购单\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.printText("\n");

                        printer.setFormat(format);
                        if (jsonObject.getInt("mode") == 1)
                            printer.printMid("-----商户存根(MERCHANT COPY)-----\n");
                        if (jsonObject.getInt("mode") == 2)
                            printer.printMid("-----客户存根(CUSTOMER COPY)-----\n");
                        printer.printText("\n");
                        printer.printText("商户名(MERCHANT NAME):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);


                        String s = jsonObject.getString("merchantName");
                        if (s.length()>16 )
                        {
                            int l = 32 -s.length();
                            for (int i=0;i<l;i++)
                            {
                                s = s+"%";
                            }
                            String str1 = s.substring(0,16);
                            String str2 = s.substring(16,32);
                            printer.printText(Printer.Alignment.LEFT, str1 + "\n");
                            printer.printText(Printer.Alignment.LEFT, str2.replace("%","") + "\n");
                        }
                        else
                            printer.printText(Printer.Alignment.LEFT, jsonObject.getString("merchantName") + "\n");

                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
//                        printer.printText("商户编号(MERCHANT NO):\n");
//                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("merchantNo") + "\n");
                        printer.printText("操作员号(OPERATOR NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("operatorNo") + "\n");
                        printer.printText("交易类型(TRANS TYPE):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("transType") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("日期/时间(DATA/TIME):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("dateTime") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("交易金额(AMOUNT):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("amount") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("合同编号(CONTRACT NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("contractNo") + "\n");
                        printer.printText("备注(REFERENCE):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("reference") + "\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printMid("--客户签名(CUSTOMER SIGNATURE)--\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText(Printer.Alignment.CENTER, "www.onlyedu.com\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.feedLine(3);
                    }

                    @Override
                    public void onFinish(int i) {
                        if (i == Printer.ERROR_NONE) {
                            EPTPrintPlugin.this.callbackContext.success("1");
                        }
                        /**
                         * Has some error. Here is display it, but you may want to hanle
                         * the error such as ERROR_OVERHEAT��ERROR_BUSY��ERROR_PAPERENDED
                         * to start again in the right time later.
                         */
                        else {
                            EPTPrintPlugin.this.callbackContext.success(getErrorDescription(i));
                        }
                    }

                    @Override
                    public void onCrash() {
                        EPTPrintPlugin.this.callbackContext.success("-1");
                    }
                };
                progress.start();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(cordova.getActivity(), "请先登录登录，错误信息:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return true;
        }

        //网银转账
        if (action.equals("printTrans")) {
//            商户存根(MERCHANT COPY)/客户存根(CUSTOMER COPY)
//            商户名(MERCHANT NAME)：上海昂立智立方教育培训有限公司
//            商户编号(MERCHANT NO)：102310082990540
//            操作员号(OPERATOR NO)：01
//            交易类型(TRANS TYPE)：现金(CASH)
//            日期/时间(DATA/TIME)：2017/07/08 18:14:32
//            交易金额(AMOUNT)：RMB 100.00
//            合同编号(CONTRACT NO)：XFSSH01201708060004
//            备注(REFERENCE)：
//            客户签名(CUSTOMER SIGNATURE)：
            final JSONObject jsonObject = args.getJSONObject(0);
            try {
                DeviceService.login(cordova.getActivity().getApplicationContext());
                Printer.Progress progress = new Printer.Progress() {
                    @Override
                    public void doPrint(Printer printer) throws Exception {
                        printer.setGray(8);
                        Printer.Format format = new Printer.Format();
                        // Use this 5x7 dot and 1 times width, 2 times height
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printMid("网银转账POS签购单\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.printText("\n");

                        printer.setFormat(format);
                        if (jsonObject.getInt("mode") == 1)
                            printer.printMid("-----商户存根(MERCHANT COPY)-----\n");
                        if (jsonObject.getInt("mode") == 2)
                            printer.printMid("-----客户存根(CUSTOMER COPY)-----\n");
                        printer.printText("\n");
                        printer.printText("商户名(MERCHANT NAME):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);

                        String s = jsonObject.getString("merchantName");
                        if (s.length()>16 )
                        {
                            int l = 32 -s.length();
                            for (int i=0;i<l;i++)
                            {
                                s = s+"%";
                            }
                            String str1 = s.substring(0,16);
                            String str2 = s.substring(16,32);
                            printer.printText(Printer.Alignment.LEFT, str1 + "\n");
                            printer.printText(Printer.Alignment.LEFT, str2.replace("%","") + "\n");
                        }
                        else
                            printer.printText(Printer.Alignment.LEFT, jsonObject.getString("merchantName") + "\n");

                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
//                        printer.printText("商户编号(MERCHANT NO):\n");
//                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("merchantNo") + "\n");
                        printer.printText("操作员号(OPERATOR NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("operatorNo") + "\n");
                        printer.printText("交易类型(TRANS TYPE):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("transType") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("日期/时间(DATA/TIME):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("dateTime") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("交易金额(AMOUNT):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("amount") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("合同编号(CONTRACT NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("contractNo") + "\n");
                        printer.printText("备注(REFERENCE):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("reference") + "\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printMid("--客户签名(CUSTOMER SIGNATURE)--\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText(Printer.Alignment.CENTER, "www.onlyedu.com\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.feedLine(3);
                    }

                    @Override
                    public void onFinish(int i) {
                        if (i == Printer.ERROR_NONE) {
                            EPTPrintPlugin.this.callbackContext.success("1");
                        }
                        /**
                         * Has some error. Here is display it, but you may want to hanle
                         * the error such as ERROR_OVERHEAT��ERROR_BUSY��ERROR_PAPERENDED
                         * to start again in the right time later.
                         */
                        else {
                            EPTPrintPlugin.this.callbackContext.success(getErrorDescription(i));
                        }
                    }

                    @Override
                    public void onCrash() {
                        EPTPrintPlugin.this.callbackContext.success("-1");
                    }
                };
                progress.start();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(cordova.getActivity(), "请先登录登录，错误信息:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return true;
        }


        //支付宝微信
        if (action.equals("printNet")) {
//            商户存根(MERCHANT COPY)/客户存根(CUSTOMER COPY)
//            商户名(MERCHANT NAME)：上海昂立智立方教育培训有限公司
//            商户编号(MERCHANT NO)：102310082990540
//            终端号(TERMINAL NO)：64367042
//            操作员号(OPERATOR NO)：01
//            商户订单号(MERCHANT ORDER NO)：7895259818924844
//            交易类型(TRANS TYPE)：支付宝付款(ALI PAY)/微信付款(WECHAT PAY)
//            客户账号(CUSTOMER ACCOUNT NO )：912***@qq.com
//                日期/时间(DATA/TIME)：2017/07/08 18:14:32
//            交易金额(AMOUNT)：RMB 100.00
//            合同编号(CONTRACT NO)：XFSSH01201708060004
//            备注(REFERENCE)：
//            客户签名(CUSTOMER SIGNATURE)：

            final JSONObject jsonObject = args.getJSONObject(0);
            try {
                DeviceService.login(cordova.getActivity().getApplicationContext());
                Printer.Progress progress = new Printer.Progress() {
                    @Override
                    public void doPrint(Printer printer) throws Exception {
                        printer.setGray(8);
                        Printer.Format format = new Printer.Format();
                        // Use this 5x7 dot and 1 times width, 2 times height

                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printMid("收钱吧POS签购单\n");

                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.printText("\n");
                        printer.setFormat(format);
                        if (jsonObject.getInt("mode") == 1)
                            printer.printMid("-----商户存根(MERCHANT COPY)-----\n");
                        if (jsonObject.getInt("mode") == 2)
                            printer.printMid("-----客户存根(CUSTOMER COPY)-----\n");
                        printer.printText("\n");
                        printer.printText("商户名(MERCHANT NAME):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);

                        String s = jsonObject.getString("merchantName");
                        if (s.length()>16 )
                        {
                            int l = 32 -s.length();
                            for (int i=0;i<l;i++)
                            {
                                s = s+"%";
                            }
                            String str1 = s.substring(0,16);
                            String str2 = s.substring(16,32);
                            printer.printText(Printer.Alignment.LEFT, str1 + "\n");
                            printer.printText(Printer.Alignment.LEFT, str2.replace("%","") + "\n");
                        }
                        else
                            printer.printText(Printer.Alignment.LEFT, jsonObject.getString("merchantName") + "\n");

                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("商户编号(MERCHANT NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("merchantNo") + "\n");
                        printer.printText("终端号(TERMINAL NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("terminalNo") + "\n");
                        printer.printText("操作员号(OPERATOR NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("operatorNo") + "\n");
                        printer.printText("商户订单号(MERCHANT ORDER NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("merchantOrderNo") + "\n");
                        printer.printText("交易类型(TRANS TYPE):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("transType") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("客户账号(CUSTOMER ACCOUNT NO ):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("customerAccountNo") + "\n");
                        printer.printText("日期/时间(DATA/TIME):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("dateTime") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("交易金额(AMOUNT):\n");
                        format.setAscScale(Printer.Format.ASC_SC1x2);
                        format.setHzScale(Printer.Format.HZ_SC1x2);
                        printer.setFormat(format);
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("amount") + "\n");
                        format.setAscScale(Printer.Format.ASC_SC1x1);
                        format.setHzScale(Printer.Format.HZ_SC1x1);
                        printer.setFormat(format);
                        printer.printText("合同编号(CONTRACT NO):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("contractNo") + "\n");
                        printer.printText("备注(REFERENCE):\n");
                        printer.printText(Printer.Alignment.LEFT, jsonObject.getString("reference") + "\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printMid("--客户签名(CUSTOMER SIGNATURE)--\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.printText(Printer.Alignment.CENTER, "www.onlyedu.com\n");
                        printer.printText("\n");
                        printer.printText("\n");
                        printer.feedLine(3);
                    }

                    @Override
                    public void onFinish(int i) {
                        if (i == Printer.ERROR_NONE) {
                            EPTPrintPlugin.this.callbackContext.success("1");
                        }
                        /**
                         * Has some error. Here is display it, but you may want to hanle
                         * the error such as ERROR_OVERHEAT��ERROR_BUSY��ERROR_PAPERENDED
                         * to start again in the right time later.
                         */
                        else {
                            EPTPrintPlugin.this.callbackContext.success(getErrorDescription(i));
                        }
                    }

                    @Override
                    public void onCrash() {
                        EPTPrintPlugin.this.callbackContext.success("-1");
                    }
                };
                progress.start();

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(cordova.getActivity(), "请先登录登录，错误信息:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return true;
        }


        return super.execute(action, args, callbackContext);
    }


    public String getErrorDescription(int code) {
        switch (code) {
            case Printer.ERROR_PAPERENDED:
                return "Paper-out, the operation is invalid this time";
            case Printer.ERROR_HARDERR:
                return "Hardware fault, can not find HP signal";
            case Printer.ERROR_OVERHEAT:
                return "Overheat";
            case Printer.ERROR_BUFOVERFLOW:
                return "The operation buffer mode position is out of range";
            case Printer.ERROR_LOWVOL:
                return "Low voltage protect";
            case Printer.ERROR_PAPERENDING:
                return "Paper-out, permit the latter operation";
            case Printer.ERROR_MOTORERR:
                return "The printer core fault (too fast or too slow)";
            case Printer.ERROR_PENOFOUND:
                return "Automatic positioning did not find the alignment position, the paper back to its original position";
            case Printer.ERROR_PAPERJAM:
                return "paper got jammed";
            case Printer.ERROR_NOBM:
                return "Black mark not found";
            case Printer.ERROR_BUSY:
                return "The printer is busy";
            case Printer.ERROR_BMBLACK:
                return "Black label detection to black signal";
            case Printer.ERROR_WORKON:
                return "The printer power is open";
            case Printer.ERROR_LIFTHEAD:
                return "Printer head lift";
            case Printer.ERROR_LOWTEMP:
                return "Low temperature protect";
        }
        return "unknown error (" + code + ")";
    }


}
