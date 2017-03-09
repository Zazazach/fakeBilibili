package lanou.com.fakebilibili.fragment;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.bean.AreaFirstBean;
import lanou.com.fakebilibili.okhttp.ICallback;
import lanou.com.fakebilibili.okhttp.OkhttpTool;
import lanou.com.fakebilibili.finaldata.*;

import static lanou.com.fakebilibili.finaldata.UrlData.AREA_FIRST;

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

public class AreaFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private AreaFirstBean areaFirstBean;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_area;
    }


    @Override
    protected void initView() {
    recyclerView=bindView(R.id.rv_area);
    }

    @Override
    protected void initData() {
        OkhttpTool.getInstance().parse(AREA_FIRST, AreaFirstBean.class, new ICallback<AreaFirstBean>() {
            @Override
            public void onSuccess(AreaFirstBean wanted) {
                areaFirstBean=wanted;

            }
            @Override
            public void onFail(Throwable throwable) {

            }
        });

    }

    @Override
    protected void bindEvent() {

    }
}
