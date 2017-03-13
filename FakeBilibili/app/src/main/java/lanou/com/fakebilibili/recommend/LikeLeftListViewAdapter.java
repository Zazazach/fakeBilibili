package lanou.com.fakebilibili.recommend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import lanou.com.fakebilibili.R;

/**
 * Created by Parcelable on 17/3/11.
 */

public class LikeLeftListViewAdapter extends BaseAdapter {
    private String[] titles;
    private Context context;
    private int index = 0;

    public int getIndex() {
        return index;
    }

    public LikeLeftListViewAdapter(Context context) {
        this.context = context;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return titles != null ? titles.length : 0;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        index = position;
        LeftViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_like_left_list_view,parent,false);
            holder = new LeftViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (LeftViewHolder) convertView.getTag();
        }
        holder.titleTv.setText(titles[position]);

        return convertView;
    }
    class LeftViewHolder{
        TextView titleTv;
        public LeftViewHolder(View convertView) {
            titleTv = (TextView) convertView.findViewById(R.id.tv_like_left_list_view_title);
        }
    }
}
