package lanou.com.fakebilibili.recommend.model;

/**
 * Created by Parcelable on 17/3/10.
 */

public interface ICallBack {
    <T>void requestData(String url,Class<T> tClass,IParse iParse);
}
