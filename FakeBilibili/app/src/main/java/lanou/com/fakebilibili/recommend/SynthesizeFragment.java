package lanou.com.fakebilibili.recommend;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.finaldata.UrlData;
import lanou.com.fakebilibili.recommend.model.Synthesize;
import lanou.com.fakebilibili.recommend.model.SynthesizeContent;
import lanou.com.fakebilibili.recommend.presenter.RecommendPresenter;
import lanou.com.fakebilibili.recommend.view.IView;
import lanou.com.fakebilibili.utils.BaseFragment;

import static lanou.com.fakebilibili.area.view.AreaFirstAdapter.getWindowManager;


/**
 * Created by Parcelable on 17/3/9.
 */

public class SynthesizeFragment extends BaseFragment implements IView {
    private RecommendPresenter presenter;
    private RecyclerView recyclerView;
    private SynthesizeRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SynthesizeContent synthesizeContent;
    private GridLayoutManager layoutManager;
    private PopupWindow popupWindow;
    private View view;
    private LinearLayout layout;

    int[] location = new int[2];

    @Override
    protected int bindLayout() {
        return R.layout.fragment_synthesize;
    }

    @Override
    protected void initView() {
        recyclerView = bindView(R.id.recycler_view_synthesize);
        swipeRefreshLayout = bindView(R.id.srl_synthesize);
        layout = bindView(R.id.layout_synthesize);
    }

    @Override
    protected void initData() {

        presenter = new RecommendPresenter(this);
        adapter = new SynthesizeRecyclerAdapter(getContext());
        layoutManager = new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        presenter.getRequestData(UrlData.RECOMMEND, Synthesize.class, 0);
        presenter.getRequestData(UrlData.RECOMMEND_SHORT, SynthesizeContent.class, 2);

        adapter.setClick(new OnItemClick() {
            @Override
            public void Jump(int pos) {
                Intent intent = new Intent(getContext(), SynthesizeContentActivity.class);
                                                                          //TODO ⬇这个地方只有一个视频 所以它就崩
                intent.putExtra("synthesizeContent", synthesizeContent.getDurl().get(0));
                startActivity(intent);
            }

            @Override
            public void popJump(int pos, View view) {
                getPopUpWindow();
                layout.setAlpha(0.2f);
                WindowManager windowManager = (WindowManager) getContext().getSystemService(getContext().WINDOW_SERVICE);
                int screenHeight = windowManager.getDefaultDisplay().getHeight();
                view.getLocationOnScreen(location);
                if (location[1] > screenHeight / 2 + 100){
                    popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,location[0],location[1] - view.getMeasuredHeight() - 80);
                }else {
                    popupWindow.showAsDropDown(view,0,0);
                }



            }
        });

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_SETTLING && layoutManager.findLastVisibleItemPosition() + 1 == adapter.getItemCount())
//                    presenter.getRequestData(UrlData.RECOMMEND, Synthesize.class, 3);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });

    }

    private void initPopWindow() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.pop_recommend_menu, null);
        popupWindow = new PopupWindow(view, 300, 90);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    layout.setAlpha(1f);

                    popupWindow = null;
                }
                return false;
            }
        });

    }

    //获取popUpWindow实例
    private void getPopUpWindow() {
        if (popupWindow != null) {
            popupWindow.dismiss();
            layout.setAlpha(1f);

        } else {
            initPopWindow();
        }
    }

    @Override
    protected void bindEvent() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getRequestData(UrlData.RECOMMEND, Synthesize.class, 1);
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

    @Override
    public <T> void loadMore(T bean) {
        Synthesize data = (Synthesize) bean;
        adapter.setSynthesize(data);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

}
