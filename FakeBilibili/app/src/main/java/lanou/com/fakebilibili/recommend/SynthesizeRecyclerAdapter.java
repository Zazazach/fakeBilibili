package lanou.com.fakebilibili.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.recommend.model.Synthesize;
import lanou.com.fakebilibili.utils.BaseViewHolder;

/**
 * Created by Parcelable on 17/3/10.
 */

public class SynthesizeRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private Synthesize synthesize;
    private OnItemClick click;
    private List<Synthesize.DataBean> dataBeen;
    private int currentPos;

    public int getCurrentPos() {

        return currentPos;

    }

    public void setDataBeen(List<Synthesize.DataBean> dataBeen) {
        this.dataBeen = dataBeen;
    }

    public void setClick(OnItemClick click) {
        this.click = click;
    }

    public SynthesizeRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setSynthesize(Synthesize synthesize) {
        this.synthesize = synthesize;
    }



    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.creatViewHolder(context,parent, R.layout.item_synthesize_recycler);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        currentPos=position;

        holder.setText(R.id.tv_synthesize,synthesize.getData().get(position).getTitle());
        holder.setImage(R.id.iv_synthesize,synthesize.getData().get(position).getCover());
        holder.setText(R.id.tv_synthesize_tag,synthesize.getData().get(position).getTname());
        holder.setText(R.id.tv_synthesize_play_size,String.valueOf(synthesize.getData().get(position).getPlay()));

        Long time = Long.valueOf(synthesize.getData().get(position).getCtime());
        Date date = new Date(time * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String times = simpleDateFormat.format(date);
        holder.setText(R.id.tv_synthesize_play_time,times);
        holder.getmView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.Jump(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return synthesize.getData() != null ? synthesize.getData().size() : 0;
    }
}
