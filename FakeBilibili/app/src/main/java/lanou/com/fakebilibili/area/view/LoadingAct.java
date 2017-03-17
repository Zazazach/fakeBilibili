package lanou.com.fakebilibili.area.view;

import android.widget.Button;
import android.widget.EditText;

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
 * Created by Zach on 17/3/16.
 */

public class LoadingAct extends BaseActivity {
    private EditText inputPhoneEt;

    private EditText inputCodeEt;

    private Button requestCodeBtn;

    private Button commitBtn;
    //
    int i = 30;
    private String phString;

    @Override
    public int bindLayout() {
        return R.layout.loading_layout;
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
