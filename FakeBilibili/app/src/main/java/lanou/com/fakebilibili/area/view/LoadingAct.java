package lanou.com.fakebilibili.area.view;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
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
 * Created by Zach on 17/3/16.
 */

public class LoadingAct extends BaseActivity implements View.OnClickListener {
    private EditText inputPhoneEt;

    private EditText inputCodeEt;

    private Button requestCodeBtn;

    private Button commitBtn;
    //
    int i = 30;
    private String phString;
    private int j;
    private Handler handler1;


    @Override
    public int bindLayout() {
        return R.layout.loading_layout;
    }

    @Override
    public void initView() {
        SMSSDK.initSDK(this, "1c27dd46978c0", "e943fc3c20265f75ac94c457578a772b");
        requestCodeBtn= (Button) findViewById(R.id.login_request_code_btn);
        commitBtn= (Button) findViewById(R.id.login_commit_btn);

        inputCodeEt= (EditText) findViewById(R.id.login_input_code_et);
        inputPhoneEt= (EditText) findViewById(R.id.login_input_phone_et);


        requestCodeBtn.setOnClickListener(this);
        commitBtn.setOnClickListener(this);
        inputCodeEt.setOnClickListener(this);
        inputPhoneEt.setOnClickListener(this);

        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);



    }

    @Override
    public void initData() {

        handler1 = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what==0){
                    requestCodeBtn.setText("请等待("+ msg.arg1 +")秒");
                    if (msg.arg1==1){
                        requestCodeBtn.setText("获取验证码");
                    }
                }

                return false;
            }
        });

    }

    @Override
    protected void bindEvent() {

    }

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                // 短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                    Toast.makeText(getApplicationContext(), "提交验证码成功",
                            Toast.LENGTH_SHORT).show();


                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {

                    Toast.makeText(getApplicationContext(), "验证码已经发送",
                            Toast.LENGTH_SHORT).show();

                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {// 返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功",
                            Toast.LENGTH_SHORT).show();


                }
            } else {
                ((Throwable) data).printStackTrace();

                Toast.makeText(LoadingAct.this, ""+data, Toast.LENGTH_LONG).show();
                Log.d("MainActivity", "data:" + data.toString());

            }

        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_request_code_btn:// 获取验证码
                if (!TextUtils.isEmpty(inputPhoneEt.getText().toString())) {
                    SMSSDK.getVerificationCode("86", inputPhoneEt.getText().toString());


                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            for (j=30; j>0 ; j--) {

                                                Thread.sleep(1000);
                                                Message message=new Message();
                                                message.arg1=j;
                                                message.what=0;

                                                handler1.sendMessage(message);
                                            }

                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }


                                    }

                                }).start();



//                    SMSSDK.getVoiceVerifyCode("86",inputPhoneEt.getText().toString());
                    phString = inputPhoneEt.getText().toString();
                } else {
                    Toast.makeText(this, "电话不能为空", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.login_commit_btn:// 校验验证码
                if (!TextUtils.isEmpty(inputCodeEt.getText().toString())) {
                    SMSSDK.submitVerificationCode("86", phString, inputCodeEt
                            .getText().toString());
                } else {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }



    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
