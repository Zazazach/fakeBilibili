package lanou.com.fakebilibili.area.view;

import lanou.com.fakebilibili.area.modle.AreaFirstBean;

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

public interface IView {
    <T> void  successData(T wanted);
    void failData();

}
