package lanou.com.fakebilibili.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

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
        

        LinearLayout layout = (LinearLayout) tabLayout.getChildAt(0);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
//        layout.setDividerPadding(params.height = 10);
        layout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        layout.setDividerDrawable(ContextCompat.getDrawable(getContext(),R.drawable.recommend_line));

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(adapter.getTitles(i));
        }

    }

    @Override
    protected void bindEvent() {

    }
}
