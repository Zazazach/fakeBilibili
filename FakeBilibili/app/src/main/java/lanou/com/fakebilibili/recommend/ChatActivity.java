package lanou.com.fakebilibili.recommend;

import android.os.Bundle;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseActivity;

/**
 * Created by Parcelable on 17/3/21.
 */

public class ChatActivity extends BaseActivity {
    @Override
    public int bindLayout() {
        return R.layout.activity_chat;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //new出EaseChatFragment或其子类的实例
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = new Bundle();
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();

    }

    @Override
    protected void bindEvent() {

    }
}
