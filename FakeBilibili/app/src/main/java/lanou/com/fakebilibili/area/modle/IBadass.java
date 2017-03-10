package lanou.com.fakebilibili.area.modle;

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

public interface IBadass {
    <T>void  getData(String url, Class<T> tClass,ICallback<T> iCallback);
}
