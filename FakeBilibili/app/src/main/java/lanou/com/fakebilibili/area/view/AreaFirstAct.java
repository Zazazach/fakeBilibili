package lanou.com.fakebilibili.area.view;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

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
 * Created by Zach on 17/3/10.
 */

public class AreaFirstAct extends BaseActivity {
    private WebView webView;
    @Override
    public int bindLayout() {
        return R.layout.area_first_act;
    }

    @Override
    public void initView() {
    webView=bindView(R.id.wv_area_first_act);
    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        String uri=intent.getStringExtra("uri");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(uri);
    }
}
