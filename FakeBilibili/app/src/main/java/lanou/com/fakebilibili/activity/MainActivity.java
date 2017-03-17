package lanou.com.fakebilibili.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import android.widget.TextView;

import android.widget.PopupWindow;

import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import lanou.com.fakebilibili.app.MyApp;
import lanou.com.fakebilibili.area.view.AreaFirstAdapter;
import lanou.com.fakebilibili.area.view.AreaFragment;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.area.view.WmData;
import lanou.com.fakebilibili.recommend.RecommendFragment;
import lanou.com.fakebilibili.utils.BaseActivity;
import lanou.com.fakebilibili.adapter.FragmentAdapter;
import lanou.com.fakebilibili.chase.chasefragment.view.ChaseFragment;


public class MainActivity extends BaseActivity {

    private List<Fragment> list;
    private FragmentAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    private final int REQUEST_CODE  = 5;



    private TextView loginNavTv;
    private ImageView loginNavIv, changeThemeNavIv, searchIv;
    private boolean isNight = false;
    private PopupWindow popupWindow;

    private WindowManager.LayoutParams lp;
    private AreaFragment areaFragment;
    private WindowManager windowManager;
    private View floatView;



    @Override
    public int bindLayout() {
        if (MyApp.appConfig.isNighTheme()) {
            this.setTheme(R.style.NightTheme);
            isNight = true;
        } else {
            this.setTheme(R.style.DayTheme);
            isNight = false;
        }
        return R.layout.activity_main;
    }


    public void initView() {

        tabLayout=bindView(R.id.tl_act_main);
        viewPager=bindView(R.id.vp_act_main);

        tabLayout = bindView(R.id.tl_act_main);
        viewPager = bindView(R.id.vp_act_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav_main);
        //初始化抽屉头视图
        View headView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        loginNavIv = (ImageView) headView.findViewById(R.id.iv_login_nav_header);
        loginNavTv = (TextView) headView.findViewById(R.id.tv_login_nav_header);
        changeThemeNavIv = (ImageView) headView.findViewById(R.id.iv_switch_nav_header);
        searchIv = bindView(R.id.iv_search_home_page);

        //沉浸式状态栏
        StatusBarUtil.setColor(this,getResources().getColor(R.color.day_title_bg));
        //抽屉的沉浸式状态栏
        StatusBarUtil.setColorForDrawerLayout(this,drawerLayout,getResources().getColor(R.color.day_title_bg));



    }

    @Override
    public void initData() {


        lp = getWindow().getAttributes();


        adapter = new FragmentAdapter(getSupportFragmentManager());
        list = new ArrayList<>();

        list.add(new RecommendFragment());
        list.add(new ChaseFragment());
        areaFragment = new AreaFragment();
        list.add(areaFragment);

        viewPager.setOffscreenPageLimit(0);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.setList(list);


    }

    //对popUpWindow进行一些初始化的设置
    protected void initPopUpWindow() {
        View popView = LayoutInflater.from(this).inflate(R.layout.pop_search_home_page, null);

        ImageView qrcodeIv = (ImageView) popView.findViewById(R.id.iv_qr_code_search_home_page);
        //扫描二维码
        qrcodeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        popupWindow = new PopupWindow(popView, 1300, 130, true);
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        popView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });

    }

    //获取popUpWindow实例
    private void getPopUpWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        } else {
            initPopUpWindow();
        }
    }

    @Override
    protected void bindEvent() {
        loginNavIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        loginNavTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        changeThemeNavIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyApp.appConfig.isNighTheme()) {
                    Toast.makeText(MainActivity.this, "切换日间模式", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "切换夜间模式", Toast.LENGTH_SHORT).show();
                }

                if (isNight) {
                    MyApp.appConfig.setNightTheme(false);
                } else {
                    MyApp.appConfig.setNightTheme(true);
                }

                Intent intent = getIntent();
                overridePendingTransition(0, R.anim.out_anim);
                finish();
                overridePendingTransition(R.anim.in_anim, 0);
                startActivity(intent);
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                }
                return false;
            }
        });

        searchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击显示搜索栏
                getPopUpWindow();

                popupWindow.showAtLocation(findViewById(R.id.lay_out_pop_home_page), Gravity.RIGHT, 0, -750);

            }
        });
    }



    //windowmanager退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       AreaFirstAdapter.getWindowManager().removeView(AreaFirstAdapter.getFloatView());

        return super.onKeyDown(keyCode, event);
    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

}
