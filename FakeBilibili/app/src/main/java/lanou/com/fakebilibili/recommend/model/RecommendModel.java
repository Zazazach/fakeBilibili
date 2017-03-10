package lanou.com.fakebilibili.recommend.model;

import android.os.Handler;
import android.os.Looper;

import lanou.com.fakebilibili.okhttp.ICallback;
import lanou.com.fakebilibili.okhttp.OkhttpTool;

/**
 * Created by Parcelable on 17/3/10.
 */

public class RecommendModel implements ICallBack{
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public <T> void requestData(String url, Class<T> tClass, final IParse iParse) {
        OkhttpTool.getInstance().parse(url, tClass, new ICallback<T>() {
            @Override
            public void onSuccess(final T wanted) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iParse.onSuccess(wanted);
                    }
                });
            }

            @Override
            public void onFail(Throwable throwable) {
                    iParse.onError();
            }
        });
    }
}
