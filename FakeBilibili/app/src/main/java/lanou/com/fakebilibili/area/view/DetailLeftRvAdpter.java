package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.area.modle.OrmBean;
import lanou.com.fakebilibili.liteorm.MyLiteOrm;
import lanou.com.fakebilibili.okhttp.ICallback;
import lanou.com.fakebilibili.okhttp.OkhttpTool;
import lanou.com.fakebilibili.utils.BaseViewHolder;
import lanou.com.fakebilibili.utils.animatorutils.MyThreadPool;

import static lanou.com.fakebilibili.finaldata.UrlData.RECOMMEND;

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
    private OrmBean ormBean;

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
    public void onBindViewHolder(final BaseViewHolder holder, final int position) {
        if (getItemViewType(position) == 1) {
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
//        if (getItemViewType(position) > 1) {
//

        ormBean = new OrmBean();

        OkhttpTool.getInstance().parse(RECOMMEND, OrmBean.class, new ICallback<OrmBean>() {
            @Override
            public void onSuccess(OrmBean wanted) {
                ormBean = wanted;
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });


        MyThreadPool.getInstance().getThreadPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {

                MyLiteOrm.getInstance().insertOrm(ormBean);

            }
        });


        try {
            Thread.sleep(500);

            ArrayList<OrmBean> list = MyLiteOrm.getInstance().queryData(OrmBean.class);

            Toast.makeText(context, "list.size()" + list.size(), Toast.LENGTH_SHORT).show();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//                    if (list != null) {
//                        ImageView imageView = holder.getView(R.id.imageView1);
//                        Glide.with(context).load(list.get(position).getData().get(0).getCover()).into(imageView);
//
//                        TextView textView = holder.getView(R.id.tv_area_first_frv_line);
//                        textView.setText(list.get(0).getData().get(position).getTitle());
//                    }


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
