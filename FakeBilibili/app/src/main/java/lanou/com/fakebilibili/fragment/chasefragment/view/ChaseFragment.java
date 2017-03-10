package lanou.com.fakebilibili.fragment.chasefragment.view;
/**
 * ██████齐天大圣 - 司帅████████
 *
 * 　　 ◢████████████████◣
 * 　　██　　　 ◥██◤　　　 ██
 * 　◢███　　　　◥◤　　　  ██◣
 * 　▊▎██◣　　　　　　　　 ◢█▊▊
 * 　▊▎██◤　　●　 　●　   ◥█▊ ▊
 * 　▊ ██　　　　　　　　　 █▊▊
 * 　◥▇██　▊　　　　　　▊　 █▇◤
 * 　　◥█　   ◥▆▄▄▆◤　    █◤　　　    ◢▇▇◣
 * ◢████◥◥▆▅▄▂▂▂▂▂▂▂▄▅▅▆▆█████◣　   ▊◢　█
 * █╳　　　　　　　　　　　　　　╳█　   ◥◤◢◤
 * ◥█◣　　　˙　　　　　˙　　　◢█◤　　   ◢◤
 * 　　▊　　　　　　　　　　　　▊　　　　█
 * 　　▊　　　　　　　　　　　　▊　　　◢◤
 * 　　▊　　　　　⊕　 　　　 　█▇▇▇◤
 * ◢█▇▆▆▆▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▆▆▆▇█◣
 * 　 ▊　▂　▊　　　　　　▊　▂　▊
 **/

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import lanou.com.fakebilibili.R;
<<<<<<< HEAD:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/fragment/ChaseFragment.java
import lanou.com.fakebilibili.utils.BaseFragment;
import lanou.com.fakebilibili.adapter.ChaseRecyclerViewAdapter;
=======

import lanou.com.fakebilibili.utils.BaseFragment;

import lanou.com.fakebilibili.adapter.ChaseRecyclerViewAdapter;


>>>>>>> 0aab56aebcfbf903f47c54cef21a3db7d76a3d1c:FakeBilibili/app/src/main/java/lanou/com/fakebilibili/fragment/chasefragment/view/ChaseFragment.java

/**
 * Created by 司帅 on 17/3/9.
 */

public class ChaseFragment extends BaseFragment {
    private RecyclerView chaseRecyclerView;
    private ChaseRecyclerViewAdapter chaseRecyclerViewAdapter;
    @Override
    protected int bindLayout() {
        return R.layout.fragment_chase;
    }

    @Override
    protected void initView() {
        chaseRecyclerView = bindView(R.id.chase_recyclerView);
    }

    @Override
    protected void initData() {
        chaseRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chaseRecyclerViewAdapter = new ChaseRecyclerViewAdapter(getActivity());
        chaseRecyclerView.setAdapter(chaseRecyclerViewAdapter);
    }

    @Override
    protected void bindEvent() {

    }
}
