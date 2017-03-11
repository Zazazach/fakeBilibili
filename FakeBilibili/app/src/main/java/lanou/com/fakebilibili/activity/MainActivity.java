package lanou.com.fakebilibili.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import lanou.com.fakebilibili.app.MyApp;
import lanou.com.fakebilibili.area.view.AreaFragment;
import lanou.com.fakebilibili.R;

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
    private ImageView loginNavIv,changeThemeNavIv;

    private boolean isNight = false;
    @Override
    public int bindLayout() {
        if(MyApp.appConfig.isNighTheme()){
            this.setTheme(R.style.NightTheme);
            isNight =  true;
        }else{
            this.setTheme(R.style.DayTheme);
            isNight = false;
        }
        return R.layout.activity_main;
    }



    public void initView() {
        tabLayout=bindView(R.id.tl_act_main);
        viewPager=bindView(R.id.vp_act_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        navigationView = (NavigationView) findViewById(R.id.nav_main);
        //初始化抽屉头视图
        View headView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        loginNavIv = (ImageView) headView.findViewById(R.id.iv_login_nav_header);
        changeThemeNavIv = (ImageView) headView.findViewById(R.id.iv_switch_nav_header);


    }

    @Override
    public void initData() {



        adapter=new FragmentAdapter(getSupportFragmentManager());
        list=new ArrayList<>();

        list.add(new RecommendFragment());
        list.add(new ChaseFragment());
        list.add(new AreaFragment());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        adapter.setList(list);

    }

    @Override
    protected void bindEvent() {
        loginNavIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "请登录", Toast.LENGTH_SHORT).show();
            }
        });
        changeThemeNavIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"切换主题",Toast.LENGTH_SHORT).show();

                if(isNight){
                    MyApp.appConfig.setNightTheme(false);
                }else{
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
                switch (item.getItemId()){
                    case R.id.iv_login_nav_header:

                        break;
                }
                return false;
            }
        });
    }
}
