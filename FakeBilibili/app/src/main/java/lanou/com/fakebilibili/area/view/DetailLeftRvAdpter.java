package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
 * Created by Zach on 17/3/11.
 */

public class DetailLeftRvAdpter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;

    public DetailLeftRvAdpter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return BaseViewHolder.creatViewHolder(context, parent, R.layout.grid_detail_left_first_line);
        } else if (viewType == 1) {
            return BaseViewHolder.creatViewHolder(context, parent, R.layout.grid_detail_left_second_line);
        } else {
            return BaseViewHolder.creatViewHolder(context, parent, R.layout.area_firest_frv_line);
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
      if(getItemViewType(position)==1) {
          holder.getView(R.id.before_detail_left).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  holder.getView(R.id.before_detail_left).setVisibility(View.GONE);
                  holder.getView(R.id.after_detail_left).setVisibility(View.VISIBLE);
              }
          });

          holder.getView(R.id.after_detail_left).setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  holder.getView(R.id.before_detail_left).setVisibility(View.VISIBLE);
                  holder.getView(R.id.after_detail_left).setVisibility(View.GONE);
              }
          });
      }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
