package lanou.com.fakebilibili.recommend;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.activity.MainActivity;
import lanou.com.fakebilibili.utils.BaseFragment;

/**
 * Created by Parcelable on 17/3/21.
 */

public class AddFrendsFragment extends BaseFragment {
    private EditText addEt;
    private Button addBtn,logoutBtn;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_addfrends;
    }

    @Override
    protected void initView() {
        addEt = bindView(R.id.et_addfrends);
        addBtn = bindView(R.id.btn_addfrends);
        logoutBtn = bindView(R.id.btn_exit);
    }

    @Override
    protected void initData() {


    }

    @Override
    protected void bindEvent() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //参数为要添加的好友的username和添加理由
                try {
                    EMClient.getInstance().contactManager().addContact(addEt.getText().toString(), "添加好友");
                    Toast.makeText(getContext(), "添加成功", Toast.LENGTH_SHORT).show();
                } catch (HyphenateException e) {
                    e.printStackTrace();
                }

            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

    }

    private void logout() {
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(getContext(),MainActivity.class));
                getActivity().finish();
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }


}
