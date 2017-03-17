package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.area.modle.AreaFirstBean;
import lanou.com.fakebilibili.ijk.IjkVideoView;
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
    private IRvClick iRvClick;
    private IBtnClick iBtnClick;

    public void setiBtnClick(IBtnClick iBtnClick) {
        this.iBtnClick = iBtnClick;
        notifyDataSetChanged();
    }

    private WmData wmData;
    private static WindowManager windowManager;
    private static View floatView;
    private String path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    public void setiRvClick(IRvClick iRvClick) {
        this.iRvClick = iRvClick;
        notifyDataSetChanged();
    }

    public static WindowManager getWindowManager() {
        return windowManager;
    }

    public static View getFloatView() {
        return floatView;
    }

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
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        int type=getItemViewType(position);
        if (type== fLine){
            //首行布局 15个模块
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

            adapter.setiRvClick(new IRvClick() {
                @Override
                public void clickMe(int position) {
                   setFloatScreen();
                }
            });

        }else if (type==nLine){
            //常规布局,一幅图而已
            String urls=areaFirstBean.getData().get(position-1).getBody().get(0).getCover();
            ImageView imageView=holder.getView(R.id.iv_area_first_nline);
            Glide.with(context).load(urls).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iRvClick.clickMe(position-1);
                }
            });


        }else {

            //Grid模式 Rv布局,注意第13位是横向的傻逼,第1位是个带banner的大哥
            Banner nBanner=holder.getView(R.id.banner);
            nBanner.setVisibility(View.GONE);

            TextView title=holder.getView(R.id.tv_frv_name);
            title.setText(areaFirstBean.getData().get(position-1).getTitle());

            RecyclerView recyclerView=holder.getView(R.id.rv_frv);

            AreaFirstFrvAdpter firstFrvAdpter=new AreaFirstFrvAdpter(context);
            firstFrvAdpter.setList(areaFirstBean.getData().get(position-1).getBody());


            GridLayoutManager layoutManager=new GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };

            if (position==13){
                layoutManager=new GridLayoutManager(context,1,LinearLayoutManager.HORIZONTAL,false){
                    @Override
                    public boolean canScrollVertically() {
                        return true;
                    }
                };
            }


            if (position==1){
                final Banner banner=holder.getView(R.id.banner);
                banner.setVisibility(View.VISIBLE);

                final ArrayList<String> imageList=new ArrayList<>();
                for (AreaFirstBean.DataBean.BannerBean.BottomBean bottomBean : areaFirstBean.getData().get(0).getBanner().getBottom()) {
                    imageList.add(bottomBean.getImage());
                }
                banner.setImageLoader(new BannerLoader()).setImages(imageList).isAutoPlay(true).setDelayTime(3000).setIndicatorGravity(BannerConfig.RIGHT).start();

                banner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(context,AreaFirstNlineAct.class);
                        String uri=areaFirstBean.getData().get(0).getBanner().getBottom().get(position-1).getUri();
                        intent.putExtra("uri",uri);
                        context.startActivity(intent);
                    }
                });
            }

            holder.getView(R.id.btn__frv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iBtnClick.clickclick();
                }
            });

            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(firstFrvAdpter);
            //里层的Grid模式适配器监听,也是把数据传入当中
            firstFrvAdpter.setiRvClick(new IRvClick() {
                @Override
                public void clickMe(int position) {

                    Intent intent=new Intent(context,AreaGridDetailAct.class);
                    context.startActivity(intent);

                }
            });

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


    private void setFloatScreen() {

        floatView = LayoutInflater.from(context).inflate(R.layout.floatscreen, null,false);

        IjkVideoView videoView = (IjkVideoView) floatView.findViewById(R.id.ijk_synthesize_content);
        TextView button= (TextView) floatView.findViewById(R.id.asdasda);


        videoView.setVideoURI(Uri.parse(path));
        videoView.start();
        windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);

        final WindowManager.LayoutParams params=new WindowManager.LayoutParams();

        params.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format= PixelFormat.RGB_565;


        params.width=800;
        params.height=300;

        params.windowAnimations = 0;
        floatView.setOnTouchListener(new View.OnTouchListener() {
            int lastX,lastY;
            int paramX,paramY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){

                    case MotionEvent.ACTION_DOWN:
                        lastX= (int) event.getRawX();
                        lastY= (int) event.getRawY();

                        paramX=params.x;
                        paramY=params.y;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        int dx= (int) (event.getRawX()-lastX);
                        int dy= (int) (event.getRawY()-lastY);

                        params.x=paramX+dx;
                        params.y=paramY+dy;

                        windowManager.updateViewLayout(floatView,params);
                        break;
                }

                return true;
            }
        });

        windowManager.addView(floatView,params);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,AreaGridDetailAct.class));
                windowManager.removeViewImmediate(floatView);
            }
        });


    }


}
