package lanou.com.fakebilibili.area.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseActivity;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 17/3/15.
 */

public class ScanAct extends BaseActivity {


    @Override
    public int bindLayout() {
        return R.layout.scandetail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        Button button= (Button) findViewById(R.id.second_button1);

        CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
                bundle.putString(CodeUtils.RESULT_STRING, result);
                resultIntent.putExtras(bundle);
                //这是结果吗?
                Toast.makeText(ScanAct.this, result, Toast.LENGTH_SHORT).show();
                ScanAct.this.setResult(RESULT_OK, resultIntent);
                ScanAct.this.finish();
            }

            @Override
            public void onAnalyzeFailed() {
                Intent resultIntent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
                bundle.putString(CodeUtils.RESULT_STRING, "");
                resultIntent.putExtras(bundle);
                ScanAct.this.setResult(RESULT_OK, resultIntent);
                ScanAct.this.finish();
            }
        };


        CaptureFragment captureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
        captureFragment.setAnalyzeCallback(analyzeCallback);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, captureFragment).commit();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void bindEvent() {

    }


}
