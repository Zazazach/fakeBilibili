package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

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
 * Created by Zach on 17/3/9.
 */

public class AreaFirstAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private AreaFirstBean areaFirstBean;
    private Context context;
    private int fLine =0;
    private int nLine=1;
    private int fRv=2;

    public void setAreaFirstBean(AreaFirstBean areaFirstBean) {
        this.areaFirstBean = areaFirstBean;
        notifyDataSetChanged();
    }

    public AreaFirstAdapter(Context context) {

        this.context = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType== fLine){
           return BaseViewHolder.creatViewHolder(context,parent, R.layout.area_first_line);
        }else if (viewType==nLine){
            return BaseViewHolder.creatViewHolder(context,parent,R.layout.area_first_nline);
        }else {
            return BaseViewHolder.creatViewHolder(context,parent,R.layout.area_first_frv);
        }



    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int type=getItemViewType(position);
        if (type== fLine){
//            Log.d("AreaFirstAdapter", areaFirstBean.getData().size()+"");
            RecyclerView firstRv=holder.getView(R.id.rv_first_line);

            AreaFirstLineAdapter adapter=new AreaFirstLineAdapter(context);
            GridLayoutManager manager=new GridLayoutManager(context,4, LinearLayoutManager.VERTICAL,false){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };


            firstRv.setLayoutManager(manager);
            firstRv.setAdapter(adapter);


        }else if (type==nLine){




        }else {


            RecyclerView recyclerView=holder.getView(R.id.rv_frv);
            AreaFirstFrvAdpter firstFrvAdpter=new AreaFirstFrvAdpter(context);
            GridLayoutManager layoutManager=new GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(firstFrvAdpter);
            Banner banner1=holder.getView(R.id.banner);
            banner1.setVisibility(View.GONE);
            if (position==1){
                Banner banner=holder.getView(R.id.banner);
                banner.setVisibility(View.VISIBLE);

                ArrayList<String> imageList=new ArrayList<>();
                for (AreaFirstBean.DataBean.BannerBean.BottomBean bottomBean : areaFirstBean.getData().get(0).getBanner().getBottom()) {
                    imageList.add(bottomBean.getImage());
                }
                banner.setImageLoader(new BannerLoader()).setImages(imageList).isAutoPlay(true).setDelayTime(3000).setIndicatorGravity(BannerConfig.RIGHT).start();

            }

        }
    }

    @Override
    public int getItemCount() {
       // Log.d("AreaFirstAdapter", "areaFirstBean.getData().size():" + areaFirstBean.getData().size());
        return areaFirstBean.getData()!=null?areaFirstBean.getData().size()+1:0;

    }

    @Override
    public int getItemViewType(int position) {

        if (position==0) {
            return fLine;
        }else if (position==2||position==4||position==7||position==9||position==11||position==18){
            return nLine;
        }else {
            return fRv;
        }

    }

    class BannerLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
