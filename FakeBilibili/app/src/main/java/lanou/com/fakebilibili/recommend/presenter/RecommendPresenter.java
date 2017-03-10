package lanou.com.fakebilibili.recommend.presenter;

import lanou.com.fakebilibili.recommend.model.ICallBack;
import lanou.com.fakebilibili.recommend.model.IParse;
import lanou.com.fakebilibili.recommend.model.RecommendModel;
import lanou.com.fakebilibili.recommend.view.IView;

/**
 * Created by Parcelable on 17/3/10.
 */

public class RecommendPresenter {
    private RecommendModel model;
    private IView iView;

    public RecommendPresenter(IView iView) {
        this.iView = iView;
        model = new RecommendModel();
    }

    public <T> void getRequestData(String url,Class<T> tClass){
        model.requestData(url, tClass, new IParse() {
            @Override
            public <T> void onSuccess(T data) {
                iView.getData(data);
            }

            @Override
            public void onError() {

            }
        });
    }
}
