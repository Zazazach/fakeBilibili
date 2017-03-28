package lanou.com.fakebilibili.chase.JcVideoPlayerActivity.view;


import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import lanou.com.fakebilibili.R;

public class JCActivity extends AppCompatActivity {

    @BindView(R.id.jc_video_player)
    JCVideoPlayerStandard jcVideoPlayerStandard;

    private String url = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";//视频网址
    private String url2 = " http://cn-lnly1-dx.acgvideo.com/vg6/8/05/14913077-1-hd.mp4?expires=1490446500&platform=android&ssig=GJbHJyARZThE55efytHbdg&oi=3066670402&nfa=FsrG6BAwqEVfeAYeXYiohg==&dynamic=1";
    private String thumb = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1491036156&di=bb3724e112b0d4c2471571aa9c7afc8c&imgtype=jpg&er=1&src=http%3A%2F%2Ff1.diyitui.com%2Fa9%2F04%2F85%2Fad%2Ff2%2Fbc%2Fb8%2Fc1%2F54%2F8f%2F24%2F4a%2F58%2Fe2%2F2a%2F80.jpg";//缩略图

    private JCVideoPlayerStandard.JCAutoFullscreenListener jcAutoFullscreenListener;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jc);
        ButterKnife.bind(this);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        jcAutoFullscreenListener = new JCVideoPlayer.JCAutoFullscreenListener();
        initJc();
    }

    private void initJc() {
        //JCVideoPlayer.SCREEN_LAYOUT_LIST JCVideoPlayer.SCREEN_LAYOUT_NORMAL JCVideoPlayer.SCREEN_WINDOW_FULLSCREEN JCVideoPlayer.SCREEN_WINDOW_TINY
        jcVideoPlayerStandard.setUp(url, JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"就喜欢嫂子");
        jcVideoPlayerStandard.loop = true;//循环播放
        jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse(thumb));

    }


    @OnClick(R.id.jc_video_player)
    public void onClick() {
    }

    @Override
    public void onBackPressed() {
        if(JCVideoPlayer.backPress()){
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(jcAutoFullscreenListener);
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(jcAutoFullscreenListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
