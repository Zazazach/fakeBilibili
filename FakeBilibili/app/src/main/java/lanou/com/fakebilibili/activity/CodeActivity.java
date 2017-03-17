package lanou.com.fakebilibili.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseActivity;

public class CodeActivity extends BaseActivity implements Handler.Callback {

    //此APPKEY仅供测试使用，且不定期失效，请到mob.com后台申请正式APPKEY
    private static String APPKEY = "1c1d525b69f28";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "e75687dfad492406f671809ba4676e02";
    private Handler handler;

    @BindView(R.id.iv_back_code)
    ImageView ivBackCode;
    @BindView(R.id.rl_title_code)
    RelativeLayout rlTitleCode;
    @BindView(R.id.tv_prompt_code)
    TextView tvPromptCode;
    @BindView(R.id.tv_phone_code)
    TextView tvPhoneCode;
    @BindView(R.id.et_code_code)
    EditText etCodeCode;
    @BindView(R.id.tv_again_code)
    TextView tvAgainCode;
    @BindView(R.id.btn_go_code)
    Button btnGoCode;
    @BindView(R.id.activity_code)
    RelativeLayout activityCode;

    @Override
    public int bindLayout() {
        return R.layout.activity_code;
    }

    @Override
    public void initView() {
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        handler = new Handler(this);
        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int i, int i1, Object o) {
                Message msg = Message.obtain();
                msg.arg1 = i;
                msg.arg2 = i1;
                msg.obj = o;
                handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    protected void bindEvent() {

    }


    @OnClick({R.id.iv_back_code, R.id.tv_phone_code, R.id.tv_again_code, R.id.btn_go_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_code:
                finish();
                break;
            case R.id.tv_phone_code:
                break;
            case R.id.tv_again_code:
                break;
            case R.id.btn_go_code:
                String code = etCodeCode.getText().toString().trim();
                SMSSDK.submitVerificationCode("+86", "15998450245", code);
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        int event = msg.arg1;
        int result = msg.arg2;
        Object data = msg.obj;

//        //当结果错误的时候
//        if (result == SMSSDK.RESULT_ERROR) {
//            //当已经提交验证码的时候
//            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                // 根据服务器返回的网络错误，给toast提示
//                try {
//                    Throwable throwable = (Throwable) data;
//                    throwable.printStackTrace();
//                    JSONObject object = new JSONObject(throwable.getMessage());
//                    String des = object.optString("detail");//错误描述
//                    int status = object.optInt("status");//错误代码
//                    if (status > 0 && !TextUtils.isEmpty(des)) {
//                        Toast.makeText(this, des, Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    //do something
//                }
//            }
//        }else if (result == SMSSDK.RESULT_COMPLETE) {
//            Log.d("RegisterActivity", "提交验证码成功");
//            //提交验证码成功
//            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE ) {
//                Toast.makeText(this, "恭喜 你的验证码正确了", Toast.LENGTH_SHORT).show();
//            }
//        }

        if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
            if (result == SMSSDK.RESULT_ERROR) {
                try {
                    Throwable throwable = (Throwable) data;
                    throwable.printStackTrace();
                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");//错误描述
                    int status = object.optInt("status");//错误代码
                    if (status > 0 && !TextUtils.isEmpty(des)) {
                        Toast.makeText(this, des, Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    //do something
                }
            }else if (result == SMSSDK.RESULT_COMPLETE){
                Toast.makeText(this, "恭喜 你的验证码正确了", Toast.LENGTH_SHORT).show();
            }
        }





        return false;
    }
}
