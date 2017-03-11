package lanou.com.fakebilibili.recommend;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.finaldata.UrlData;
import lanou.com.fakebilibili.recommend.model.Synthesize;
import lanou.com.fakebilibili.recommend.model.SynthesizeContent;
import lanou.com.fakebilibili.recommend.presenter.RecommendPresenter;
import lanou.com.fakebilibili.recommend.view.IView;
import lanou.com.fakebilibili.utils.BaseFragment;



/**
 * Created by Parcelable on 17/3/9.
 */

public class SynthesizeFragment extends BaseFragment implements IView{
    private RecommendPresenter presenter;
    private RecyclerView recyclerView;
    private SynthesizeRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SynthesizeContent synthesizeContent;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_synthesize;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_synthesize);
        swipeRefreshLayout = bindView(R.id.srl_synthesize);
    }

    @Override
    protected void initData() {
        presenter = new RecommendPresenter(this);
        adapter = new SynthesizeRecyclerAdapter(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2, LinearLayoutManager.VERTICAL,false));
        presenter.getRequestData(UrlData.RECOMMEND, Synthesize.class,0);
        presenter.getRequestData(UrlData.RECOMMEND_SHORT,SynthesizeContent.class,3);
        adapter.setClick(new OnItemClick() {
            @Override
            public void Jump(int pos) {
                Intent intent = new Intent(getContext(),SynthesizeContentActivity.class);
                intent.putExtra("synthesizeContent",synthesizeContent.getDurl().get(pos));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void bindEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getRequestData(UrlData.RECOMMEND, Synthesize.class,1);
            }
        });

    }

    @Override
    public <T> void getData(T bean) {
        Synthesize synthesize = (Synthesize) bean;
        adapter.setSynthesize(synthesize);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public <T> void refresh(T bean) {
        Synthesize synthesize = (Synthesize) bean;
        adapter.setSynthesize(synthesize);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public <T> void getContentData(T bean) {
        synthesizeContent = (SynthesizeContent) bean;
    }
}
