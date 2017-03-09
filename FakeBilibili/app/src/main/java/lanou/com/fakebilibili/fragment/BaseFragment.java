package lanou.com.fakebilibili.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Parcelable on 17/3/9.
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(bindLayout(),container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        bindEvent();
    }
    protected abstract int bindLayout();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void bindEvent();
    public <T extends View> T bindView (int resId){
        return (T) getView().findViewById(resId);
    }
}
