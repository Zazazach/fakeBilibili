package lanou.com.fakebilibili.area.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import lanou.com.fakebilibili.R;
import lanou.com.fakebilibili.activity.MainActivity;
import lanou.com.fakebilibili.app.MyApp;
import lanou.com.fakebilibili.utils.BaseFragment;

import static com.amap.api.maps.AMap.MAP_TYPE_NORMAL;

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

public class AreaGridDetailRightFragment extends Fragment implements LocationSource, AMapLocationListener, View.OnClickListener {
    private RecyclerView recyclerView;

    private AMap aMap = null;
    private MapView mMapView = null;
    private MyLocationStyle myLocationStyle;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption = null;
    private LocationSource.OnLocationChangedListener mListener;
    public AMapLocationClient mLocationClient = null;
    private UiSettings mUiSettings;//定义一个UiSettings对象

    private Button standard, satellite, night, search;
    private EditText keyword;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {


        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {

            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //定位成功回调信息，设置相关消息
                    //获取纬度
                    latitude = aMapLocation.getLatitude();
                    //获取经度
                    longitude = aMapLocation.getLongitude();

                } else {
                    //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }

        }
    };
    private View view;

    private static final String TAG = "AreaGridDetailRightFrag";
    private PoiSearch poiSearch;
    private PoiSearch.Query query;
    private double longitude;
    private double latitude;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //   Log.e(TAG, "onViewCreated: "+savedInstanceState.getString("aaa") );
        view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_grid_detail_right, null);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView = (MapView) getView().findViewById(R.id.map1);
        mMapView.onCreate(savedInstanceState);

        standard = (Button) view.findViewById(R.id.btn1);
        satellite = (Button) view.findViewById(R.id.btn2);
        night = (Button) view.findViewById(R.id.btn3);
        search = (Button) view.findViewById(R.id.btn4);
        keyword = (EditText) view.findViewById(R.id.et_map);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMay();
        standard.setOnClickListener(this);
        satellite.setOnClickListener(this);
        night.setOnClickListener(this);

        searchPOI();
    }


    //测试本studio的Key值
    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    //地图第一步 定位 蓝标志
    private void initMay() {

        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图


        //  Log.d("MainActivity", sHA1(this));


        if (aMap == null) {

            aMap = mMapView.getMap();
            Log.d("ssss", "dddd");
            aMap.setLocationSource(this);
// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            aMap.setMyLocationEnabled(true);
// 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
            aMap.setMapType(AMap.LOCATION_TYPE_MAP_FOLLOW);
        }

        // 设置定位监听


        mLocationClient = new AMapLocationClient(MyApp.getContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        mlocationClient.setLocationListener(this);

        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setOnceLocationLatest(true);
        mLocationOption.setInterval(2000);
        mLocationOption.setNeedAddress(true);


        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();

        //获取定位结果

        AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {

                if (amapLocation != null) {

                    Log.d("MainActivity1", amapLocation.getCity());//城市信息
                    if (amapLocation.getErrorCode() == 0) {


                        amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        amapLocation.getLatitude();//获取纬度
                        amapLocation.getLongitude();//获取经度
                        amapLocation.getAccuracy();//获取精度信息
                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        amapLocation.getCountry();//国家信息
                        amapLocation.getProvince();//省信息
                        Log.d("MainActivity", amapLocation.getCity());//城市信息

                        Log.d("MainActivity", amapLocation.getDistrict());//城区信息
                        amapLocation.getStreet();//街道信息
                        amapLocation.getStreetNum();//街道门牌号信息
                        amapLocation.getCityCode();//城市编码
                        amapLocation.getAdCode();//地区编码
                        amapLocation.getAoiName();//获取当前定位点的AOI信息
                        amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        amapLocation.getFloor();//获取当前室内定位的楼层
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);
//可在其中解析amapLocation获取相应内容。
                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
            }
        };


        mUiSettings = aMap.getUiSettings();
        mUiSettings.setCompassEnabled(true);


    }


    //第一步所实现的方法
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mlocationClient == null) {
            //初始化定位
            mlocationClient = new AMapLocationClient(MyApp.getContext());
            //初始化定位参数
            mLocationOption = new AMapLocationClientOption();
            //设置定位回调监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);

            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();//启动定位
        }
    }

    @Override
    public void deactivate() {

        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (mListener != null && aMapLocation != null) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {


                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }


        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {

                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间

            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }

    }


    //不同生命周期的处理
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "llll");

        mMapView.onDestroy();
        if (null != mlocationClient) {
            mlocationClient.onDestroy();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        // outState.putString("aaa","hahaha");
        Toast.makeText(getContext(), "ppp", Toast.LENGTH_SHORT).show();
        mMapView.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                break;

            case R.id.btn2:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                break;

            case R.id.btn3:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);
                break;
        }
    }

    // 地图第二步  根据ID检索POI
    public void searchPOI() {

        Toast.makeText(getContext(), "search1", Toast.LENGTH_SHORT).show();

        if (keyword.getText().toString() != null)




        view.findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String words = keyword.getText().toString()==null?"景点":keyword.getText().toString();


                query = new PoiSearch.Query(words, "餐饮服务|购物服务|生活服务|地名地址信息|公共设施", "");
                query.setPageSize(8);
                query.setPageNum(5);
                poiSearch = new PoiSearch(getContext(), query);
                poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latitude,longitude),9000000));


                poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
                    @Override
                    public void onPoiSearched(PoiResult poiResult, int i) {
                        Toast.makeText(getContext(), ""+(poiResult!=null), Toast.LENGTH_SHORT).show();

                        Log.d(TAG, "poiResult.getPois().get(0).getDistance():" + poiResult.getPois().get(0).getDistance());

                        for (PoiItem poiItem : poiResult.getPois()) {

                            Log.d(TAG, "1111");
                        }
                    }

                    @Override
                    public void onPoiItemSearched(PoiItem poiItem, int i) {

                    }
                });

                poiSearch.searchPOIAsyn();

            }
        });

    }



//        if (i == 1000) {
//            Log.i("---","查询结果:"+i);
//            if (poiResult != null && poiResult.getQuery() != null) {// 搜索poi的结果
//                if (poiResult.getQuery().equals(query)) {// 是否是同一条
//                    PoiResult poiResults = poiResult;
//                    // 取得搜索到的poiitems有多少页
//                    List<PoiItem> poiItems = poiResult.getPois();// 取得第一页的poiitem数据，页数从数字0开始
//                    List<SuggestionCity> suggestionCities = poiResult
//                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
//
//                    if (poiItems != null && poiItems.size() > 0) {
//                        aMap.clear();// 清理之前的图标
////                        PoiOverlay poiOverlay = new PoiOverlay(aMap, poiItems);
////                        poiOverlay.removeFromMap();
////                        poiOverlay.addToMap();
////                        poiOverlay.zoomToSpan();
//                    } else if (suggestionCities != null
//                            && suggestionCities.size() > 0) {
////                        showSuggestCity(suggestionCities);
//                    } else {
//                        Toast.makeText(getContext(), "未找到结果",Toast.LENGTH_SHORT).show();
//                    }
//                }
//            } else {
//                Toast.makeText(getContext(), "该距离内没有找到结果",Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Log.i("---","查询结果:"+i);
//            Toast.makeText(getContext(), "异常代码---"+i,Toast.LENGTH_SHORT).show();
//        }


}
