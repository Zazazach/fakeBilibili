package lanou.com.fakebilibili.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD

=======



import lanou.com.fakebilibili.area.view.AreaFragment;
import lanou.com.fakebilibili.R;

import lanou.com.fakebilibili.recommend.RecommendFragment;
import lanou.com.fakebilibili.utils.BaseActivity;

import lanou.com.fakebilibili.area.view.AreaFragment;


import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.adapter.FragmentAdapter;

>>>>>>> d15a53e9ef358bae142a6826a461136470958642
import lanou.com.fakebilibili.area.view.AreaFragment;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.recommend.RecommendFragment;
import lanou.com.fakebilibili.utils.BaseActivity;
import lanou.com.fakebilibili.adapter.FragmentAdapter;
<<<<<<< HEAD
import lanou.com.fakebilibili.fragment.chasefragment.view.ChaseFragment;

=======





import lanou.com.fakebilibili.area.view.AreaFragment;
import lanou.com.fakebilibili.fragment.chasefragment.view.ChaseFragment;





import lanou.com.fakebilibili.utils.BaseActivity;


>>>>>>> d15a53e9ef358bae142a6826a461136470958642
public class MainActivity extends BaseActivity {

    private List<Fragment> list;
    private FragmentAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }



    public void initView() {
        tabLayout=bindView(R.id.tl_act_main);
        viewPager=bindView(R.id.vp_act_main);
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
}
