package lanou.com.fakebilibili.area.view;


import android.content.Intent;
import android.content.SharedPreferences;
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

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
  //  String path = "http://vod.lexue.com/video/5e4f6939170318.mp4";

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
    private boolean isNight=false;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.initialize(this);
        sharedPreferences=getPreferences(MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //设置夜间模式
        if (sharedPreferences.getInt("theme",0)!=0){
            setTheme(sharedPreferences.getInt("theme",0));
        }

        Log.d("AreaGridDetailAct", "sharedPreferences.getInt " + sharedPreferences.getInt("theme", 0));
        setContentView(R.layout.area_grid_detail_act);


//        findViewById(R.id.tv444).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(AreaGridDetailAct.this,MapAct.class));
//                finish();
//            }
//        });

        //设置维他命Player
        vitaminoPlayer();

        //抽屉设置
        drawerSetting();


        fragmentArea();




        ImageView scan= (ImageView) findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AreaGridDetailAct.this, ScanAct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(AreaGridDetailAct.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


    private void fragmentArea() {
        //视频下方的区域

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

    private void drawerSetting() {
        //抽屉布局的功能实现  点击 夜间模式
        drawerLayout= (DrawerLayout) findViewById(R.id.drawerer);
        navigationView= (NavigationView) findViewById(R.id.nav);


        //通过这种方法 来吧header的布局引过来 来设置点击监听
        // style 以int形式, 是否点击ischecked 存入sp
        View head=navigationView.inflateHeaderView(R.layout.drawer_header);
        checkBox= (CheckBox) head.findViewById(R.id.mode);
        if (sharedPreferences.getInt("checked",1)==1){
            checkBox.setChecked(true);
            Log.d("AreaGridDetailAct", "1111");
        }else {
            checkBox.setChecked(false);
            Log.d("AreaGridDetailAct", "2222");
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked){
                    setTheme(R.style.area_nighttime);
                    isNight=true;
                    editor.clear();
                    editor.putInt("theme",R.style.area_nighttime);
                    editor.putInt("checked",1);
                    editor.commit();
                    //为了重新运行到onCreate
                    Intent intent=new Intent(AreaGridDetailAct.this, AreaGridDetailAct.class);
                    finish();
                    startActivity(intent);
                }else {
                    setTheme(R.style.area_daytime);
                    isNight=false;
                    editor.clear();
                    editor.putInt("theme",R.style.area_daytime);
                    editor.putInt("checked",0);
                    editor.commit();

                    Intent intent=new Intent(AreaGridDetailAct.this, AreaGridDetailAct.class);
                    finish();
                    startActivity(intent);

                }

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.v1:
                        Log.d("AreaGridDetailAct", "v1");
                        break;
                    case R.id.v2:
                        Log.d("AreaGridDetailAct", "v2");
                        break;
                }
                return false;
            }
        });
    }

    private void vitaminoPlayer() {
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
