package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.area.modle.AreaFirstBean;
import lanou.com.fakebilibili.area.presenter.MyPrexenter;
import lanou.com.fakebilibili.ijk.IjkVideoView;
import lanou.com.fakebilibili.utils.BaseFragment;

import static android.content.Context.WINDOW_SERVICE;
import static lanou.com.fakebilibili.area.modle.UrlData.AREA_FIRST;

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

public class AreaFragment extends BaseFragment implements IView {
    private RecyclerView recyclerView;
    private AreaFirstBean areaFirstBean;
    private AreaFirstAdapter areaFirstTvAdapter;
    private MyPrexenter myPrexenter;

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

        myPrexenter=new MyPrexenter(this);
        myPrexenter.wayToData(AREA_FIRST,AreaFirstBean.class);

        areaFirstTvAdapter=new AreaFirstAdapter(getContext());
        LinearLayoutManager manager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);


        areaFirstTvAdapter.setiRvClick(new IRvClick() {
            @Override
            public void clickMe(int position) {
                myPrexenter.interClick(position);
            }
        });



        areaFirstTvAdapter.setiBtnClick(new IBtnClick() {
            @Override
            public void clickclick() {
                //跳指定页面
                startActivity(new Intent(getContext(),LoadingAct.class));
            }
        });
    }

    @Override
    protected void bindEvent() {


    }






    @Override
    public <T> void successData(T wanted) {
        areaFirstBean=new AreaFirstBean();
        areaFirstBean= (AreaFirstBean) wanted;
        Log.d("AreaFragment", areaFirstBean.getData().get(0).getTitle());
        areaFirstTvAdapter.setAreaFirstBean(areaFirstBean);
        recyclerView.setAdapter(areaFirstTvAdapter);
    }

    @Override
    public void failData() {

    }

    @Override
    public void rvClick(int position) {
        Intent intent=new Intent(getActivity(),AreaFirstNlineAct.class);
        intent.putExtra("uri",areaFirstBean.getData().get(position).getBody().get(0).getUri());
        startActivity(intent);
    }





}
