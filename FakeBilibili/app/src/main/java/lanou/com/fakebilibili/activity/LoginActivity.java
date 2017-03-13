package lanou.com.fakebilibili.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.app.MyApp;
import lanou.com.fakebilibili.utils.BaseActivity;

public class LoginActivity extends BaseActivity {
    private EditText userEt,passwordEt;
    private ImageView openCloseIv,backIv, userIconIv, passwordIconIv,userLineIv,passwordLineIv;
    private TextView loginTv;
    @Override
    public int bindLayout() {
        if(MyApp.appConfig.isNighTheme()){
            this.setTheme(R.style.NightTheme);
        }else{
            this.setTheme(R.style.DayTheme);
        }
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        backIv = bindView(R.id.iv_back_login);
        openCloseIv = bindView(R.id.iv_open_and_close);
        loginTv = bindView(R.id.tv_login_login);
        userEt = bindView(R.id.et_user_login);
        userIconIv = bindView(R.id.iv_user_icon_login);
        userLineIv = bindView(R.id.iv_line_user_login);
        passwordEt = bindView(R.id.et_password_login);
        passwordIconIv = bindView(R.id.iv_password_icon_login);
        passwordLineIv = bindView(R.id.iv_line_password_login);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.day_title_bg));
    }

    @Override
    public void initData() {

    }

    @Override
    protected void bindEvent() {
        userEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    userIconIv.setImageResource(R.mipmap.icon_user_selected);
                    userLineIv.setBackgroundResource(R.color.day_title_bg);
                }else {
                    userIconIv.setImageResource(R.mipmap.icon_user);
                    userLineIv.setBackgroundResource(R.color.line);
                }
            }
        });
        userEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String passwordText = passwordEt.getText().toString();
                ChangeLoginState(s, passwordText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    openCloseIv.setImageResource(R.mipmap.close_eye);
                    passwordIconIv.setImageResource(R.mipmap.icon_password_selected);
                    passwordLineIv.setBackgroundResource(R.color.day_title_bg);
                }else {
                    openCloseIv.setImageResource(R.mipmap.open_eye);
                    passwordIconIv.setImageResource(R.mipmap.icon_password);
                    passwordLineIv.setBackgroundResource(R.color.line);
                }
            }
        });
        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String userText = userEt.getText().toString();
                ChangeLoginState(s, userText);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void ChangeLoginState(CharSequence s1, String s2) {
        if ( !TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)){
            loginTv.setBackgroundResource(R.color.colorAccent);
            loginTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }else{
            loginTv.setBackgroundResource(R.color.loginBg);
        }
    }
}
