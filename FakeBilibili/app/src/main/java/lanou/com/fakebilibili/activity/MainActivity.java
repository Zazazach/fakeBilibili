package lanou.com.fakebilibili.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD


import lanou.com.fakebilibili.area.view.AreaFragment;
import lanou.com.fakebilibili.R;

import lanou.com.fakebilibili.recommend.RecommendFragment;
import lanou.com.fakebilibili.utils.BaseActivity;

import lanou.com.fakebilibili.area.view.AreaFragment;


import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.adapter.FragmentAdapter;


=======
import lanou.com.fakebilibili.area.view.AreaFragment;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.recommend.RecommendFragment;
import lanou.com.fakebilibili.utils.BaseActivity;
import lanou.com.fakebilibili.adapter.FragmentAdapter;

>>>>>>> 04405b2bd1134099538ee04e2476e69ae4a16d13


<<<<<<< HEAD

import lanou.com.fakebilibili.area.view.AreaFragment;
=======
>>>>>>> 04405b2bd1134099538ee04e2476e69ae4a16d13
import lanou.com.fakebilibili.fragment.chasefragment.view.ChaseFragment;





<<<<<<< HEAD
import lanou.com.fakebilibili.utils.BaseActivity;
=======
>>>>>>> 04405b2bd1134099538ee04e2476e69ae4a16d13


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
