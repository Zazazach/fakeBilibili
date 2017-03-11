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

    public <T> void getRequestData(String url,Class<T> tClass,final int type){

        model.requestData(url, tClass, new IParse() {
            @Override
            public <T> void onSuccess(T data) {
                switch (type){
                    case 0:
                        iView.getData(data);
                        break;
                    case 1:
                        iView.refresh(data);
                        iView.hideRefresh();
                        break;
                    case 2:
                        iView.getContentData(data);
                        break;
                    case 3:
                        iView.loadMore(data);
                        break;

                }
            }

            @Override
            public void onError() {

            }
        });
    }



}
