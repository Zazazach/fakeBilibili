package lanou.com.fakebilibili.recommend;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.recommend.model.Title;
import lanou.com.fakebilibili.recommend.presenter.RecommendPresenter;
import lanou.com.fakebilibili.recommend.view.IView;
import lanou.com.fakebilibili.utils.BaseActivity;

/**
 * Created by Parcelable on 17/3/9.
 */

public class LikeActivity extends BaseActivity{
    private ListView leftListView;
    private LikeLeftListViewAdapter leftListViewAdapter;
    private Title title;
    @Override
    public int bindLayout() {
        return R.layout.activity_like;
    }

    @Override
    public void initView() {
        leftListView = bindView(R.id.list_view_like_left);

    }

    @Override
    public void initData() {
        List<Title> titles = new ArrayList<>();
        Title title = new Title();
        for (int i = 0; i < 11; i++) {
            title.setTitles("动画");
            title.setTitles("音乐");
            title.setTitles("舞蹈");
            title.setTitles("游戏");
            title.setTitles("科技");
            title.setTitles("生活");
            title.setTitles("鬼畜");
            title.setTitles("时尚");
            title.setTitles("娱乐");
            title.setTitles("电影");
            title.setTitles("电视剧");

            titles.add(title);
        }

        leftListViewAdapter = new LikeLeftListViewAdapter(this);
        leftListViewAdapter.setTitles(titles);
        leftListView.setAdapter(leftListViewAdapter);


    }

    @Override
    protected void bindEvent() {
        findViewById(R.id.iv_like_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }




}
