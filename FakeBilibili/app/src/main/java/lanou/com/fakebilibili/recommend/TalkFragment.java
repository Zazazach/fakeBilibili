package lanou.com.fakebilibili.recommend;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseFragment;

import static lanou.com.fakebilibili.R.id.tv_register_login;

/**
 * Created by Parcelable on 17/3/9.
 */

public class TalkFragment extends BaseFragment implements View.OnClickListener {
    private EditText nameEt, passwordEt;
    private TextView loginTv, registerTv;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_talk;
    }

    @Override
    protected void initView() {
        nameEt = bindView(R.id.et_user_login);
        passwordEt = bindView(R.id.et_password_login);
        loginTv = bindView(R.id.tv_login_login);
        registerTv = bindView(tv_register_login);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {
        loginTv.setOnClickListener(this);
        registerTv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case tv_register_login:
                register();
                break;
            case R.id.tv_login_login:
                login();
                break;
        }
    }

    //注册的方法
    private void register() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(nameEt.getText().toString().trim(), passwordEt.getText().toString().trim());
                    Log.d("TalkFragment", "注册成功");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    Log.d("TalkFragment", "注册失败");
                }
            }
        }).start();

    }

    //登录的方法
    private void login() {
        EMClient.getInstance().login(nameEt.getText().toString().trim(), passwordEt.getText().toString().trim(), new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(getContext(),TalkActivity.class));
            }

            @Override
            public void onError(int i, String s) {
                Log.d("TalkFragment", i + " " + s.toString());
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });

    }
}
