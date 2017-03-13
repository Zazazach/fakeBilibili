package lanou.com.fakebilibili.area.view;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;
import lanou.com.fakebilibili.R;

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

public class AreaGridDetailAct extends AppCompatActivity {

    String path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DetailFragmentAdapter adapter;
    private List<Fragment> list;
    private Toolbar toolbar;
    private CircleImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.initialize(this);
        setContentView(R.layout.area_grid_detail_act);
        VideoView videoView = (VideoView) findViewById(R.id.surface_view);
        imageView = (CircleImageView) findViewById(R.id.iv_float_grid_detail_act);

            videoView.setVideoURI(Uri.parse(path));
//           TODO 视频管理先放一下
//            MediaController controller = new MediaController(this);
//            videoView.setMediaController(controller);
            videoView.start();
        viewPager= (ViewPager) findViewById(R.id.vp__grid_detail_act);
        tabLayout= (TabLayout) findViewById(R.id.tl_grid_detail_act);
        list=new ArrayList<>();
        list.add(new AreaGridDetailLeftFragment());
        list.add(new AreaGridDetailRightFragment());
        adapter=new DetailFragmentAdapter(getSupportFragmentManager());
        adapter.setList(list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //设置滑动的toolbar透明
        toolbar = (Toolbar) findViewById(R.id.toolbar_grid_detail_act);
        toolbar.setAlpha(0f);

        EventBus.getDefault().register(this);
    }



    /**
     *
     * @param eventBean
     */

    @Subscribe
    public void onB(EventBean eventBean){
        //注意正确引包

        if (eventBean.getType()>0.5f){
            imageView.setVisibility(View.INVISIBLE);
        }else {
            imageView.setVisibility(View.VISIBLE);
        }
        toolbar.setAlpha(eventBean.getType());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
