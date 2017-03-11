package lanou.com.fakebilibili.recommend;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.finaldata.UrlData;
import lanou.com.fakebilibili.recommend.model.Synthesize;
import lanou.com.fakebilibili.recommend.presenter.RecommendPresenter;
import lanou.com.fakebilibili.recommend.view.IView;
import lanou.com.fakebilibili.utils.BaseFragment;

import lanou.com.fakebilibili.utils.BaseFragment;
import lanou.com.fakebilibili.ijk.IjkVideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

import lanou.com.fakebilibili.utils.BaseFragment;

import lanou.com.fakebilibili.ijk.IjkVideoView;

/**
 * Created by Parcelable on 17/3/9.
 */

public class SynthesizeFragment extends BaseFragment implements IView{
    private RecommendPresenter presenter;
    private RecyclerView recyclerView;
    private SynthesizeRecyclerAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_synthesize;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_synthesize);
    }

    @Override
    protected void initData() {
        presenter = new RecommendPresenter(this);
        adapter = new SynthesizeRecyclerAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false));
        presenter.getRequestData(UrlData.RECOMMEND, Synthesize.class);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    public <T> void getData(T bean) {
        Synthesize synthesize = (Synthesize) bean;
        adapter.setSynthesize(synthesize);
        recyclerView.setAdapter(adapter);

    }
}
