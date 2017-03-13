package lanou.com.fakebilibili.recommend;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseActivity;

/**
 * Created by Parcelable on 17/3/9.
 */

public class LikeActivity extends BaseActivity {
    private ListView leftListView,rightListView;
    private LikeLeftListViewAdapter leftListViewAdapter;
    private String[] tTitles = {"动画","音乐","舞蹈","游戏","科技","生活","鬼畜","时尚","娱乐","电影","电视剧"};
    private int pos = 0;

    @Override
    public int bindLayout() {
        return R.layout.activity_like;
    }

    @Override
    public void initView() {
        leftListView = bindView(R.id.list_view_like_left);
        rightListView = bindView(R.id.list_view_like_right);
    }

    @Override
    public void initData() {
        leftListViewAdapter = new LikeLeftListViewAdapter(this);
        leftListViewAdapter.setTitles(tTitles);
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

        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = leftListViewAdapter.getIndex();
                switch (pos) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                }

            }
        });
    }
}
