package lanou.com.fakebilibili.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.recommend.model.Title;

/**
 * Created by Parcelable on 17/3/11.
 */

public class LikeLeftListViewAdapter extends BaseAdapter {
    private List<Title> titles;
    private Context context;

    public LikeLeftListViewAdapter(Context context) {
        this.context = context;
    }

    public void setTitles(List<Title> titles) {
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles != null ? titles.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_like_left_list_view,parent,false);
            holder = new LeftViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (LeftViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(titles.get(position).getTitles());

        return convertView;
    }
    class LeftViewHolder{
        TextView titleTv;
        public LeftViewHolder(View convertView) {
            titleTv = (TextView) convertView.findViewById(R.id.tv_like_left_list_view_title);
        }
    }
}
