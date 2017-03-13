package lanou.com.fakebilibili.area.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseFragment;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 17/3/11.
 */

public class AreaGridDetailLeftFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private DetailLeftRvAdpter adpter;
    private int length=0;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_grid_detail_left;
    }

    @Override
    protected void initView() {
        recyclerView=bindView(R.id.rv_fragment_grid_detail_left);
    }

    @Override
    protected void initData() {
        adpter=new DetailLeftRvAdpter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adpter);
        EventBus.getDefault().post(new EventBean());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                EventBean eventBean=new EventBean();
                length+=dy;
            if (length<140f){
                eventBean.setType(length/140f);
                EventBus.getDefault().post(eventBean);
            }
            }
        });
    }

    @Override
    protected void bindEvent() {

    }
}
