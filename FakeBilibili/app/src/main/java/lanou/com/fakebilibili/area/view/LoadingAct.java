package lanou.com.fakebilibili.area.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseActivity;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 17/3/15.
 */

public class LoadingAct extends BaseActivity {


    @BindView(R.id.profile_head)
    ImageView profileHead;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_login_captcha)
    TextView tvLoginCaptcha;
    @BindView(R.id.iv_weibo_login)
    ImageView ivWeiboLogin;
    @BindView(R.id.iv_wechat_login)
    ImageView ivWechatLogin;
    @BindView(R.id.iv_qq_login)
    ImageView ivQqLogin;

    @Override
    public int bindLayout() {
        return R.layout.login_layou;
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
