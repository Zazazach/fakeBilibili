package lanou.com.fakebilibili.adapter;
/**
 * ██████齐天大圣 - 司帅████████
 * <p>
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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseViewHolder;

/**
 * Created by 司帅 on 17/3/9.
 */

public class ChaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int HEAD = -1;
    private static final int MIDDLE = -2;
    private static final int BODY = -3;
    private Context context;

    public ChaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEAD:
                return BaseViewHolder.creatViewHolder(context,parent, R.layout.item_chase_head);
            case MIDDLE:
                return BaseViewHolder.creatViewHolder(context,parent,R.layout.item_chase_middle);
            case BODY:
                return BaseViewHolder.creatViewHolder(context,parent,R.layout.item_chase_body);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD;
        } else if (position == 1) {
            return MIDDLE;
        } else {
            return BODY;
        }
    }
}
