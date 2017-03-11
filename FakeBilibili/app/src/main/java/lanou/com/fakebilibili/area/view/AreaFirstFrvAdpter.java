package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.area.modle.AreaFirstBean;
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
 * Created by Zach on 17/3/10.
 */

public class AreaFirstFrvAdpter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<AreaFirstBean.DataBean.BodyBean> list;
    private IRvClick iRvClick;

    public void setiRvClick(IRvClick iRvClick) {
        this.iRvClick = iRvClick;
        notifyDataSetChanged();
    }

    public void setList(List<AreaFirstBean.DataBean.BodyBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public AreaFirstFrvAdpter(Context context) {
        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.creatViewHolder(context,parent, R.layout.area_firest_frv_line);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        ImageView imageView=holder.getView(R.id.imageView);
        Glide.with(context).load(list.get(position).getCover()).into(imageView);

        TextView textView=holder.getView(R.id.tv_area_first_frv_line);
        textView.setText(list.get(position).getTitle());

        TextView play=holder.getView(R.id.tv_frv_line_play);
        play.setText(list.get(position).getPlay()+"");

        TextView danmaku=holder.getView(R.id.tv_frv_line_comment);
        danmaku.setText(list.get(position).getDanmaku()+"");

        holder.getView(R.id.whole_area_first_frv_line).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iRvClick.clickMe(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }
}
