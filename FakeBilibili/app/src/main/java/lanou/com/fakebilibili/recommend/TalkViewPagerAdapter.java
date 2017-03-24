package lanou.com.fakebilibili.recommend;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Parcelable on 17/3/20.
 */

public class TalkViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] titles = {"会话","通讯录","添加好友"};

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public TalkViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        Log.d("TalkViewPagerAdapter", "fragments.size():" + fragments.size());
        return fragments != null ? fragments.size() : 0;
    }
    public String getTitles(int pos){
        return titles[pos];
    }
}
