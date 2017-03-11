package lanou.com.fakebilibili.recommend;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.ijk.IjkVideoView;
import lanou.com.fakebilibili.recommend.model.SynthesizeContent;
import lanou.com.fakebilibili.utils.BaseActivity;

/**
 * Created by Parcelable on 17/3/10.
 */

public class SynthesizeContentActivity extends BaseActivity {
    private IjkVideoView videoView;
    @Override
    public int bindLayout() {
        return R.layout.activity_synthesizecontent;
    }

    @Override
    public void initView() {
        videoView = bindView(R.id.ijk_synthesize_content);

    }

    @Override
    public void initData() {
        SynthesizeContent.DurlBean durlBean = getIntent().getParcelableExtra("synthesizeContent");
        String path = durlBean.getUrl();
        videoView.setVideoPath(path);
        videoView.start();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        videoView.stopPlayback();
    }
}
