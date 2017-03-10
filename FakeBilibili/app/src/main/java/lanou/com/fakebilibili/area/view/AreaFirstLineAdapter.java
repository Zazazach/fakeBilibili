package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseViewHolder;

/**
 * .       _ooOoo_
 * .      o8888888o
 * .      88" . "88
 * .      (| -_- |)
 * .      O\  =  /O
 * .   ____/`---'\____
 * . .'  \\|     |//  `.
 * ./  \\|||  :  |||//  \
 * Created by Zach on 17/3/9.
 */

public class AreaFirstLineAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;

    public AreaFirstLineAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return BaseViewHolder.creatViewHolder(context,parent, R.layout.area_first_line_line);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
