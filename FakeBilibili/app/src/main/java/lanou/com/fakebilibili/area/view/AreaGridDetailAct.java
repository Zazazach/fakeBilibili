package lanou.com.fakebilibili.area.view;


import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

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
    private ImageView back;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private CheckBox checkBox;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.initialize(this);
        setContentView(R.layout.area_grid_detail_act);
        final VideoView videoView = (VideoView) findViewById(R.id.surface_view);
        imageView = (CircleImageView) findViewById(R.id.iv_float_grid_detail_act);
        back= (ImageView) findViewById(R.id.iv_back_grid_detail_act);
        videoView.setVideoURI(Uri.parse(path));
//           TODO 视频管理先放一下
//            MediaController controller = new MediaController(this);
//            videoView.setMediaController(controller);

        videoView.start();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AreaGridDetailAct", "videoView.isPlaying():" + videoView.isPlaying());
                if (videoView.isPlaying()) {
                    videoView.pause();
                }else {
                    videoView.resume();
                }
            }
        });


        checkBox= (CheckBox) findViewById(R.id.mode);

        drawerLayout= (DrawerLayout) findViewById(R.id.drawerer);
        navigationView= (NavigationView) findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.rv_menu:
                        Toast.makeText(AreaGridDetailAct.this, "11", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.mode:

                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked){
                                    checkBox.setChecked(false);
                                   // TODO 夜间模式
                                }else {
                                    checkBox.setChecked(true);
                                }
                            }
                        });
                        break;
                }
                return false;
            }
        });








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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EventBus.getDefault().register(this);
    }


    /**
     *
     * @param eventBean
     */

    @Subscribe
    public void onB(EventBean eventBean){
        //注意正确引包

        if (eventBean.getType()>0.2f){
            imageView.setVisibility(View.INVISIBLE);
            toolbar.setAlpha(1);
        }else {
            imageView.setVisibility(View.VISIBLE);
            toolbar.setAlpha(0);
        }

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }



//    //屏幕变暗 1s内
//    private void dimBackground(final float from, final float to) {
//        final Window window = getWindow();
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
//        valueAnimator.setDuration(1000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//            }
//        });
//
//        valueAnimator.start();
//    }
}
