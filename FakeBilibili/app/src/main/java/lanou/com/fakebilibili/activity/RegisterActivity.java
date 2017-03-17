package lanou.com.fakebilibili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.app.MyApp;

public class RegisterActivity extends AppCompatActivity implements Handler.Callback{

    //此APPKEY仅供测试使用，且不定期失效，请到mob.com后台申请正式APPKEY
    private static String APPKEY = "1c1d525b69f28";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "e75687dfad492406f671809ba4676e02";
    private Handler handler;


    @BindView(R.id.iv_back_register)
    ImageView ivBackRegister;
    @BindView(R.id.rl_title_register)
    RelativeLayout rlTitleRegister;
    @BindView(R.id.tv_countries_and_phone_register)
    TextView tvCountriesAndPhoneRegister;
    @BindView(R.id.tv_country_register)
    TextView tvCountryRegister;
    @BindView(R.id.rl_countries_register)
    RelativeLayout rlCountriesRegister;
    @BindView(R.id.tv_choose_country)
    TextView tvChooseCountry;
    @BindView(R.id.et_phone_register)
    EditText etPhoneRegister;
    @BindView(R.id.rl_phone_register)
    RelativeLayout rlPhoneRegister;
    @BindView(R.id.btn_get_code_register)
    Button btnGetCodeRegister;
    @BindView(R.id.activity_register)
    RelativeLayout activityRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(MyApp.appConfig.isNighTheme()){
            this.setTheme(R.style.NightTheme);
        }else{
            this.setTheme(R.style.DayTheme);
        }
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        SMSSDK.initSDK(this,APPKEY,APPSECRET);

        handler = new Handler(this);
        EventHandler eh = new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object o) {
                Message msg = Message.obtain();
                msg.arg1 = i;
                msg.arg2 = i1;
                msg.obj = o ;
              handler.sendMessage(msg);
            }
        };
        SMSSDK.registerEventHandler(eh);


////       语音验证码方法
//        SMSSDK.getVoiceVerifyCode("+86","15945150843");
    }

    @OnClick({R.id.iv_back_register, R.id.rl_title_register, R.id.tv_countries_and_phone_register, R.id.tv_country_register, R.id.rl_countries_register, R.id.tv_choose_country, R.id.btn_get_code_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_register:
                finish();
                break;
            case R.id.tv_countries_and_phone_register:
                break;
            case R.id.tv_country_register:
                break;
            case R.id.rl_countries_register:
                break;
            case R.id.tv_choose_country:
                break;
            case R.id.btn_get_code_register:
                /**
                 * getVerificationCode用于向服务器请求发送验证码的服务，需要传递国家代号和接收验证码的手机号码，
                 * 支持此服务的国家代码在 getSupportedCountries中获取。请求getVerificationCode的时间间隔不应该小于60秒，
                 * 否则服务端会返回“操作过 于频繁”的错误
                 * submitVerificationCode用于向服务器提交接收到的短信验证码，
                 * 验证成功后会通过EventHandler返回国家代码和电话号码。
                 */
                SMSSDK.getSupportedCountries();
                SMSSDK.getVerificationCode("+86", "15998450245", new OnSendMessageHandler() {
                    //第一个参数是国家 第二个参数是电话号
                    //其中OnSendMessageHandler的定义如下，这个Handler的用途是在发送短信之前，开发者自己执行一个操作，来根据电话号码判断是否需要发送短信
                    @Override
                    public boolean onSendMessage(String s, String s1) {
                        Log.e("RegisterActivity", s+" "+s1);
                        return false;
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁 否则可能造成内存泄漏
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public boolean handleMessage(Message msg) {
        int event = msg.arg1;
        int result = msg.arg2;
        Object data = msg.obj;
        if (data instanceof Throwable){
            Throwable th = (Throwable) data;
            th.printStackTrace();
        }
        if (result == SMSSDK.RESULT_COMPLETE){
            //回调完成
            Toast.makeText(RegisterActivity.this, "请稍微等下验证码", Toast.LENGTH_SHORT).show();

            if (event ==  SMSSDK.EVENT_GET_VERIFICATION_CODE ){
                //获取验证码成功
                Log.e("RegisterActivity", "获取验证码成功");
                startActivity(new Intent(RegisterActivity.this,CodeActivity.class));
            }
        }
        return false;
    }
}
