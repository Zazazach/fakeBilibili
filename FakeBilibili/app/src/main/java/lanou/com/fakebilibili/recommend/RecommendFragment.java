package lanou.com.fakebilibili.recommend;

<<<<<<< HEAD
=======
import android.content.Intent;
<<<<<<< HEAD:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/recommend/RecommendFragment.java
=======
import android.graphics.Color;
>>>>>>> 02802c3895ee18db0945a8f53d73d3a968476038
>>>>>>> e2608e84aa291b7e5e405ab84ca740ec74c9c028:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/fragment/RecommendFragment.java
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.activity.LikeActivity;
import lanou.com.fakebilibili.adapter.RecommendPagerAdapter;
<<<<<<< HEAD:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/recommend/RecommendFragment.java
import lanou.com.fakebilibili.fragment.BaseFragment;
=======
import lanou.com.fakebilibili.utils.BaseFragment;
>>>>>>> e2608e84aa291b7e5e405ab84ca740ec74c9c028:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/fragment/RecommendFragment.java

/**
 * Created by Parcelable on 17/3/9.
 */

public class RecommendFragment extends BaseFragment {
    private RecommendPagerAdapter adapter;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private ImageView likeIv;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        tabLayout = bindView(R.id.tab_recommend);
        viewPager = bindView(R.id.view_pager_recommend);
        likeIv = bindView(R.id.iv_recommend_like_tab);
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

        //画两个tab之间的线
        LinearLayout layout = (LinearLayout) tabLayout.getChildAt(0);
        layout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        layout.setDividerDrawable(ContextCompat.getDrawable(getContext(),R.drawable.recommend_line));
        layout.setDividerPadding(50);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(adapter.getTitles(i));
        }

        likeIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LikeActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void bindEvent() {

    }
}
