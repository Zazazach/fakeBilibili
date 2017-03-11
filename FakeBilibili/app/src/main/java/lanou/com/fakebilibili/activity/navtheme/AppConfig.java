package lanou.com.fakebilibili.activity.navtheme;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import lanou.com.fakebilibili.app.MyApp;
import lanou.com.fakebilibili.utils.SPUtils;

/**
 * Created by cxm on 2016/8/17.
 */
public class AppConfig {

    private static final String KEY_NIGHT_MODE_SWITCH="night_theme";

    //夜间模式
    public boolean isNighTheme() {
        return (boolean) SPUtils.get(KEY_NIGHT_MODE_SWITCH, false);
    }

    public void setNightTheme(boolean on) {
        SPUtils.put(KEY_NIGHT_MODE_SWITCH, on);
    }
    /**
     * 清空
     */
    public void clear() {
        SPUtils.clear(MyApp.getContext());
    }


}
