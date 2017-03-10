package lanou.com.fakebilibili.area.presenter;

import lanou.com.fakebilibili.area.modle.IBadass;
import lanou.com.fakebilibili.area.modle.MyModle;
import lanou.com.fakebilibili.area.view.IView;
import lanou.com.fakebilibili.okhttp.ICallback;

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

public class MyPrexenter<T> {
    private MyModle myModle;
    private IView iView;

    public MyPrexenter(IView iView) {
        this.iView = iView;
        myModle=new MyModle();
    }

    public void wayToData(String url, final Class<T> tClass){
        myModle.getData(url, tClass, new ICallback<T>() {
            @Override
            public void onSuccess(T wanted) {
                iView.successData(wanted);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
    }

    public void interClick(int position){
        iView.rvClick(position);
    }



}
