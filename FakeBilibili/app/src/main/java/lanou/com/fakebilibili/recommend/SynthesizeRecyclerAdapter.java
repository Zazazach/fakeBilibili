package lanou.com.fakebilibili.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_synthesize,synthesize.getData().get(position).getTitle());
        holder.setImage(R.id.iv_synthesize,synthesize.getData().get(position).getCover());

    }

    @Override
    public int getItemCount() {
        return synthesize.getData() != null ? synthesize.getData().size() : 0;
    }
}
