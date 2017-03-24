package lanou.com.fakebilibili.recommend;

import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMContactListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContact;
import com.hyphenate.chat.EMContactManager;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseActivity;

/**
 * Created by Parcelable on 17/3/17.
 */

public class TalkActivity extends BaseActivity {
    private static final String TAG = "TalkActivity";
    private List<Fragment> fragments;
    private TalkViewPagerAdapter adapter;
    private ViewPager viewPager;
    private EaseContactListFragment contactListFragment;
    private TabLayout tabLayout;

    @Override
    public int bindLayout() {
        return R.layout.activity_talk;
    }

    @Override
    public void initView() {
        viewPager = bindView(R.id.view_Pager_talk);
        tabLayout = bindView(R.id.tab_talk);
    }

    @Override
    public void initData() {
        //启动会话列表
        EaseConversationListFragment conversationListFragment = new EaseConversationListFragment();
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                startActivity(new Intent(TalkActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
            }
        });


        //直接启动联系人列表
        contactListFragment = new EaseContactListFragment();
        //需要设置联系人列表才能启动fragment
        new Thread(new Runnable() {
            @Override
            public void run() {
                contactListFragment.setContactsMap(getContacts());
            }
        }).start();
        //设置item点击事件
        contactListFragment.setContactListItemClickListener(new EaseContactListFragment.EaseContactListItemClickListener() {

            @Override
            public void onListItemClicked(EaseUser user) {
                startActivity(new Intent(TalkActivity.this, ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, user.getUsername()));
            }
        });


        fragments = new ArrayList<>();
        fragments.add(conversationListFragment);
        fragments.add(contactListFragment);
        fragments.add(new AddFrendsFragment());
        adapter = new TalkViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setText(adapter.getTitles(i));
        }


        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {


            @Override
            public void onContactInvited(String username, String reason) {
                //收到好友邀请
                Log.e(TAG, "onContactInvited: " + username);

            }

            @Override
            public void onFriendRequestAccepted(String s) {
                Log.e(TAG, "onFriendRequestAccepted: " + s);
            }

            @Override
            public void onFriendRequestDeclined(String s) {
                Log.e(TAG, "onFriendRequestDeclined: " + s);
            }

            @Override
            public void onContactDeleted(String username) {
                //被删除时回调此方法
                Log.e(TAG, "onContactDeleted: " + username);
            }


            @Override
            public void onContactAdded(String username) {
                //增加了联系人时回调此方法
                Log.e(TAG, "onContactAdded: " + username);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        contactListFragment.setContactsMap(getContacts());
                        contactListFragment.refresh();
                    }
                }).start();

            }
        });


    }

    @Override
    protected void bindEvent() {

    }

    /**
     * 获取联系人
     *
     * @return
     */
    private Map<String, EaseUser> getContacts() {
        Map<String, EaseUser> map = new HashMap<>();
        try {
            List<String> userNames = EMClient.getInstance().contactManager().getAllContactsFromServer();
//            KLog.e("......有几个好友:" + userNames.size());
            for (String userId : userNames) {
//                KLog.e("好友列表中有 : " + userId);
                map.put(userId, new EaseUser(userId));
            }
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return map;
    }

}
