package lanou.com.fakebilibili.area.modle;

import android.os.Handler;
import android.os.Looper;

import lanou.com.fakebilibili.okhttp.ICallback;
import lanou.com.fakebilibili.okhttp.OkhttpTool;

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

public class MyModle implements IBadass {
    private Handler handler=new Handler(Looper.getMainLooper());


    @Override
    public <T> void getData(String url, Class<T> tClass, final ICallback<T> iCallback) {
        OkhttpTool.getInstance().parse(url, tClass, new ICallback<T>() {
            @Override
            public void onSuccess(final T wanted) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    iCallback.onSuccess(wanted);
                    }
                });
            }

            @Override
            public void onFail(Throwable throwable) {
                    iCallback.onFail(throwable);
            }
        });
    }
}
