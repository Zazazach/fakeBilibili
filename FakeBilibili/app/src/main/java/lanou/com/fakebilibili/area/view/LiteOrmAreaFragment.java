package lanou.com.fakebilibili.area.view;

import android.support.v7.widget.RecyclerView;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.liteorm.MyLiteOrm;
import lanou.com.fakebilibili.utils.BaseFragment;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 2017/3/22.
 */

public class LiteOrmAreaFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private LiteRvAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.liteorm_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void bindEvent() {

    }
}
