package lanou.com.fakebilibili.recommend.view;

import lanou.com.fakebilibili.recommend.model.ICallBack;

/**
 * Created by Parcelable on 17/3/10.
 */

public interface IView {
    <T> void getData(T bean);
}
