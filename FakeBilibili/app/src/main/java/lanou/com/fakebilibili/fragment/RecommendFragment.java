package lanou.com.fakebilibili.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.adapter.RecommendPagerAdapter;

/**
 * Created by Parcelable on 17/3/9.
 */

public class RecommendFragment extends BaseFragment{
    private RecommendPagerAdapter adapter;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private ViewPager viewPager;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tab_recommend);
        viewPager = bindView(R.id.view_pager_recommend);

    }

    @Override
    protected void initData() {
        fragments = new ArrayList<>();
        fragments.add(new SynthesizeFragment());
        fragments.add(new DynamicFragment());
        adapter = new RecommendPagerAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    protected void bindEvent() {

    }
}
