package lanou.com.fakebilibili.recommend.view;


/**
 * Created by Parcelable on 17/3/10.
 */

public interface IView {
    <T> void getData(T bean);
    <T> void refresh(T bean);
    void hideRefresh();
    <T> void getContentData(T bean);
    <T> void loadMore(T bean);
}
