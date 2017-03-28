package lanou.com.fakebilibili.chase.chasefragment.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.amap.api.services.core.PoiItem;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.utils.BaseViewHolder;

import java.util.List;

/**
 * Created by sishuai on 2017/3/23.
 */

public class ListViewAdapter extends BaseAdapter{
    private List<PoiItem> poiItems;// poi数据

    public void setPoiItems(List<PoiItem> poiItems) {
        this.poiItems = poiItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return poiItems == null ? 0 : poiItems.size();
    }

    @Override
    public Object getItem(int position) {
        return poiItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder baseViewHolder = BaseViewHolder.creatListViewHolder(convertView,parent, R.layout.item_chase_address);
        View itemView = baseViewHolder.getmView();
        PoiItem poiItem = poiItems.get(position);
        baseViewHolder.setText(R.id.item_title , poiItem.getTitle());
        baseViewHolder.setText(R.id.item_text , poiItem.getSnippet());
        return itemView;
    }

}
