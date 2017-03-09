package lanou.com.fakebilibili.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 17/3/9.
 */

public class BaseMethod implements ISimplify {
    private OkHttpClient okHttpClient;
    private Gson gson;
    private Handler handler=new Handler(Looper.getMainLooper());

    public BaseMethod() {
        gson=new Gson();
        okHttpClient=new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(5000, TimeUnit.MILLISECONDS).build();
    }

    @Override
    public <T> void parse(String url, final Class<T> tClass, final ICallback<T> callback) {
        Request request=new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFail(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string=response.body().string();
                final T content=gson.fromJson(string,tClass);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(content);
                    }
                });
            }
        });
    }
}
