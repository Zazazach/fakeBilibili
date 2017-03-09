package lanou.com.fakebilibili.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Parcelable on 17/3/9.
 */

public class RecommendPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public RecommendPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments != null ? fragments.size() : 0;
    }
}
