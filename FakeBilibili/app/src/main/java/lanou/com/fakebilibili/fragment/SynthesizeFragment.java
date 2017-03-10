package lanou.com.fakebilibili.fragment;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.ijk.IjkVideoView;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Parcelable on 17/3/9.
 */

public class SynthesizeFragment extends BaseFragment {
    private IjkVideoView videoView;
    private String url = "http://cn-tj4-cu.acgvideo.com/vg3/c/06/14772544-1-hd.mp4?expires=1489122600&platform=android&ssig=Di3hDHOmByow2dn2uo9Ccw&oi=2056146356&nfa=RGeBDJQwd8EMY10XEydOtw==&dynamic=1";

    @Override
    protected int bindLayout() {
        return R.layout.fragment_synthesize;
    }

    @Override
    protected void initView() {
        videoView = bindView(R.id.video_view);

    }

    @Override
    protected void initData() {

        videoView.setVideoPath(url);
        videoView.start();




    }

    @Override
    protected void bindEvent() {

    }

}
