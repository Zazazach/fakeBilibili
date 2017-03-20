package lanou.com.fakebilibili.app;
/**
 * ██████齐天大圣 - 司帅████████
 * <p>
 * 　　 ◢████████████████◣
 * 　　██　　　 ◥██◤　　　 ██
 * 　◢███　　　　◥◤　　　  ██◣
 * 　▊▎██◣　　　　　　　　 ◢█▊▊
 * 　▊▎██◤　　●　 　●　   ◥█▊ ▊
 * 　▊ ██　　　　　　　　　 █▊▊
 * 　◥▇██　▊　　　　　　▊　 █▇◤
 * 　　◥█　   ◥▆▄▄▆◤　    █◤　　　    ◢▇▇◣
 * ◢████◥◥▆▅▄▂▂▂▂▂▂▂▄▅▅▆▆█████◣　   ▊◢　█
 * █╳　　　　　　　　　　　　　　╳█　   ◥◤◢◤
 * ◥█◣　　　˙　　　　　˙　　　◢█◤　　   ◢◤
 * 　　▊　　　　　　　　　　　　▊　　　　█
 * 　　▊　　　　　　　　　　　　▊　　　◢◤
 * 　　▊　　　　　⊕　 　　　 　█▇▇▇◤
 * ◢█▇▆▆▆▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▆▆▆▇█◣
 * 　 ▊　▂　▊　　　　　　▊　▂　▊
 **/

import android.app.Application;
import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.jpush.android.api.JPushInterface;
import lanou.com.fakebilibili.activity.navtheme.AppConfig;

/**
 * Created by 司帅 on 17/3/11.
 */

public class MyApp extends Application {
    private static Context context;
    public static AppConfig appConfig;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        appConfig = new AppConfig();
        ZXingLibrary.initDisplayOpinion(this);
        /**
         * 即时通讯的初始化
         */

        EaseUI.getInstance().init(this, null);
        EMClient.getInstance().setDebugMode(true);

        JPushInterface.init(this);
        //可打印错误信息
        JPushInterface.setDebugMode(true);
    }

    public static Context getContext() {
        return context;
    }
}
