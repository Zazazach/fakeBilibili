package lanou.com.fakebilibili.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/MainActivity.java
import lanou.com.fakebilibili.area.view.AreaFragment;
=======
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.adapter.FragmentAdapter;
import lanou.com.fakebilibili.fragment.AreaFragment;
>>>>>>> 02802c3895ee18db0945a8f53d73d3a968476038:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/activity/MainActivity.java
import lanou.com.fakebilibili.fragment.ChaseFragment;
<<<<<<< HEAD
import lanou.com.fakebilibili.recommend.RecommendFragment;
=======
import lanou.com.fakebilibili.fragment.RecommendFragment;
import lanou.com.fakebilibili.utils.BaseActivity;
>>>>>>> e2608e84aa291b7e5e405ab84ca740ec74c9c028

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
