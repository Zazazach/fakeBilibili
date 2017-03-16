package lanou.com.fakebilibili.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.app.MyApp;

public class RegisterActivity extends AppCompatActivity {

    //此APPKEY仅供测试使用，且不定期失效，请到mob.com后台申请正式APPKEY
    private static String APPKEY = "f3fc6baa9ac4";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static String APPSECRET = "7f3dedcb36d92deebcb373af921d635a";

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


        EventHandler eh = new EventHandler(){
            @Override
            public void afterEvent(int i, int i1, Object o) {
                if(i == SMSSDK.EVENT_GET_CONTACTS){
                    Log.d("RegisterActivity", o.getClass().getName());
                }
            }
        };
        SMSSDK.registerEventHandler(eh);
        SMSSDK.getSupportedCountries();
        SMSSDK.getVerificationCode("+86", "18641116968", new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                Log.d("RegisterActivity", s);
                return false;
            }
        });
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
                break;
        }
    }
}
