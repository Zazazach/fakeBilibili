package lanou.com.fakebilibili.recommend.model;

/**
 * Created by Parcelable on 17/3/10.
 */

public interface IParse {
    <T>void onSuccess(T data);
    void onError();
}
