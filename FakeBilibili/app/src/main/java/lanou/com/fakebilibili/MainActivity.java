package lanou.com.fakebilibili;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import lanou.com.fakebilibili.fragment.AreaFragment;
import lanou.com.fakebilibili.fragment.ChaseFragment;
import lanou.com.fakebilibili.fragment.RecommendFragment;

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
