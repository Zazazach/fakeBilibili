package lanou.com.fakebilibili.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.app.MyApp;
import lanou.com.fakebilibili.utils.BaseActivity;

public class LoginActivity extends BaseActivity {

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

    }

    @Override
    public void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
