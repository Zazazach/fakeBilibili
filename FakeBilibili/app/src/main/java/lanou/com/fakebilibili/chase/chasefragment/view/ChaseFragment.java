package lanou.com.fakebilibili.chase.chasefragment.view;
/**
 * ██████齐天大圣 - 司帅████████
 *
 * 　　 ◢████████████████◣
 * 　　██　　　 ◥██◤　　　 ██
 * 　◢███　　　　◥◤　　　  ██◣
 * 　▊▎██◣　　　　　　　　 ◢█▊▊
 * 　▊▎██◤　　●　 　●　   ◥█▊ ▊
 * 　▊ ██　　　　　　　　　 █▊▊
 * 　◥▇██　▊　　　　　　▊　 █▇◤
 * 　　◥█　   ◥▆▄▄▆◤　    █◤　　　    ◢▇▇◣
 * ◢████◥◥▆▅▄▂▂▂▂▂▂▂▄▅▅▆▆█████◣　   ▊◢　█
 * █╳　　　　　　　　　　　　　　╳█　   ◥◤◢◤
 * ◥█◣　　　˙　　　　　˙　　　◢█◤　　   ◢◤
 * 　　▊　　　　　　　　　　　　▊　　　　█
 * 　　▊　　　　　　　　　　　　▊　　　◢◤
 * 　　▊　　　　　⊕　 　　　 　█▇▇▇◤
 * ◢█▇▆▆▆▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▆▆▆▇█◣
 * 　 ▊　▂　▊　　　　　　▊　▂　▊
 **/

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.List;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.app.MyApp;
import lanou.com.fakebilibili.chase.JcVideoPlayerActivity.view.JCActivity;
import lanou.com.fakebilibili.chase.chasefragment.adapter.ListViewAdapter;
import lanou.com.fakebilibili.utils.BaseFragment;
import lanou.com.fakebilibili.chase.chasefragment.adapter.ChaseRecyclerViewAdapter;


/**
 * Created by 司帅 on 17/3/9.
 */

public class ChaseFragment extends BaseFragment implements View.OnClickListener, LocationSource, AMapLocationListener, PoiSearch.OnPoiSearchListener, AMap.OnCameraChangeListener {
    private Button toJcBtn,backBtn,toAppBtn;
    private MapView mapView;
    private ListView mapList;
    private AMapLocationClient mLocationClient;
    private LocationSource.OnLocationChangedListener mListener;
    private LatLng latlng;
    private String city;
    private AMap aMap;
    private String deepType = "";// poi搜索类型
    private PoiSearch.Query query;// Poi查询条件类
    private PoiSearch poiSearch;
    private PoiResult mPoiResult; // poi返回的结果

    private List<PoiItem> poiItems;// poi数据
    private Button mButton;
    private ListViewAdapter adapter;

    private boolean isFirstLoc = true;
    private AMapLocation mAMapLocation;//当前位置
    @Override
    protected int bindLayout() {
        return R.layout.fragment_chase;
    }

    @Override
    protected void initView() {
        toJcBtn = (Button) getView().findViewById(R.id.btn_to_jc_player);
        backBtn = bindView(R.id.btn_back);
        toAppBtn = bindView(R.id.btn_to_map_app);
        mapView = bindView(R.id.map_local);
        mapList = bindView(R.id.map_list);

    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            //可视范围改变时回调此方法。这个方法必须在主线程中调用。
            aMap.setOnCameraChangeListener(this);
            setUpMap();
        }

        deepType = "餐饮服务";//这里以餐饮为例
    }

    private void setUpMap() {
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(MyApp.getContext());
            AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setOnceLocation(true);
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mLocationClient.setLocationOption(mLocationOption);
            mLocationClient.startLocation();
        }
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.locat_marker));// 设置小蓝点的图标

        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
    }

    @Override
    protected void initData() {
        init();
    }

    @Override
    protected void bindEvent() {
        toJcBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        toAppBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_jc_player:
                startActivity(new Intent(getActivity(), JCActivity.class));
                break;
            case R.id.btn_back:
                this.onLocationChanged(mAMapLocation);
                break;
            case R.id.btn_to_map_app:
                Intent intent = MyApp.getContext().getPackageManager().getLaunchIntentForPackage("com.sishuai.mapdemo");
                if (intent != null){
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "请下载这个应用", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        mLocationClient.startLocation();
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                if (isFirstLoc){
                    mAMapLocation = aMapLocation;
                    isFirstLoc = false;
                }
                // 显示我的位置
                mListener.onLocationChanged(aMapLocation);
                //设置第一次位置焦点中心
                latlng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 17), 1000, null);
                city = aMapLocation.getProvince();

                //Marker 是在地图上的一个点绘制图标。这个图标和屏幕朝向一致，和地图朝向无关。
                //初始化Marker构造器对象
                MarkerOptions markerOptions = new MarkerOptions();
                //设置当前MarkerOptions 对象的经纬度。
                markerOptions.position(new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude()));
                //设置Markey的标题
                markerOptions.title("当前位置");
                //设置Marker是否可见
                markerOptions.visible(true);
                //设置Marker图标
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
                        .fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.locat_marker));
                markerOptions.icon(bitmapDescriptor);
                //aMap增加Marker
//                aMap.addMarker(markerOptions);

                doSearchQuery();
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }
    //搜索
    private void doSearchQuery() {
        aMap.setOnMapClickListener(null);// 进行poi搜索时清除掉地图点击事件
        int currentPage = 0;
        // 第一个参数表示搜索字符串，
        // 第二个参数表示poi搜索类型，
        // POI搜索类型共分为以下20种：汽车服务|汽车销售|
        //汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        // 第三个参数表示poi搜索区域（空字符串代表全国）可以是城市编码也可以是城市名称
        query = new PoiSearch.Query("", deepType, city);
        query.setPageSize(20);// 设置每页最多返回多少条poiitem
        query.setPageNum(currentPage);// 设置查第一页 （设置查询页码）

        poiSearch = new PoiSearch(getActivity(), query);
        poiSearch.setOnPoiSearchListener(this);

        LatLonPoint lp = new LatLonPoint(latlng.latitude, latlng.longitude);
        //适用于搜索某个位置附近的POI，可设置POI的类别，具体查询所在位置的餐饮类、住宅类POI，例如：查找天安门附近的厕所等等场景。
        // 与关键字检索的唯一区别需要通过 PoiSearch 的 setBound 方法设置圆形查询范围。
        // 设置搜索区域为以lp点为圆心，其周围2000米范围
        poiSearch.setBound(new PoiSearch.SearchBound(lp, 5000, true));
        poiSearch.searchPOIAsyn();// 异步搜索
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (i == 1000) {
            if (poiResult != null && poiResult.getQuery() != null) {// 搜索poi的结果
                if (poiResult.getQuery().equals(query)) {// 是否是同一条
                    this.mPoiResult = poiResult;
                    poiItems = mPoiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
                    if (poiItems != null && poiItems.size() > 0) {
                        adapter = new ListViewAdapter ();
                        adapter.setPoiItems(poiItems);
                        mapList.setAdapter(adapter);
                        mapList.setOnItemClickListener(new mOnItemClickListener());
                    }
                }else {
                    Toast.makeText(getActivity(), "无结果", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getActivity(), "无结果 i != 0", Toast.LENGTH_SHORT).show();
            }
        } else if (i == 27) {
            Toast.makeText(getActivity(), "error_network", Toast.LENGTH_SHORT).show();
        } else if (i == 32) {
            Toast.makeText(getActivity(), "error_key", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "error_other：" + i, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }
    //用户对地图做出一系列改变地图可视区域的操作（如拖动、动画滑动、缩放）完成之后回调此方法。这个方法必须在主线程中调用。
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        latlng = cameraPosition.target;
        aMap.clear();
        aMap.addMarker(new MarkerOptions().position(latlng));
        //查询附近
        doSearchQuery();
    }

    private class mOnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //getLatLonPoint()忽的POI的经纬坐标
            LatLng newLatLng = new LatLng(poiItems.get(position).getLatLonPoint().getLatitude()
                    ,poiItems.get(position).getLatLonPoint().getLongitude());
            //可视区域动画是指从当前可视区域转换到一个指定位置的可视区域的过程，moveCamera(CameraUpdate update)是按照传入的CameraUpdate 参数移动可视区域。
            // 在转换完成后,将回调转入的AMap.CancelableCallback接口。填空为不回调
            //返回一个CameraUpdate 对象，包括可视区域框移动目标点屏幕中心位置的经纬度以及缩放级别
            aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng,20), 1000, null);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        mLocationClient.startLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocationClient.stopLocation();
    }

    @Override
    public void onDestroy() {
        mLocationClient.onDestroy();
        super.onDestroy();
    }

}
