package com.hebut.triptogether.UI.Address;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.hebut.triptogether.R;
import com.baidu.mapapi.SDKInitializer;
import com.hebut.triptogether.UI.Main.starty;
import com.hebut.triptogether.UI.Route.Route_optimization;

import java.util.List;

public class AddressFragment extends Fragment {
    final String[] arr_province={"北京市","天津市","上海市","广东省","河南省","重庆市","河北省","山西省","辽宁省","吉林省",
            "黑龙江省","江苏省","浙江省", "安徽省","福建省","江西省","山东省","湖北省","湖南省","海南省","四川省","贵州省",
            "云南省","陕西省", "甘肃省","青海省","台湾省"};
    final String [][]arr_city=new String[][] {
            {"北京市"},// 北京市
            {"天津市"},//天津市
            {"上海市"},//上海市
            {"广州", "深圳", "韶关", "珠海", "汕头", "佛山", "湛江", "肇庆", "江门", "茂名", "惠州", "梅州",
                    "汕尾", "河源", "阳江", "清远", "东莞", "中山", "潮州", "揭阳", "云浮"},//广州市
            {"郑州市","开封市","洛阳市","平顶山市","许昌市"},//河南省

    };
    final String[][][]arr_district= new String [][][]{
            {//北京市
                    {"东城区", "西城区", "崇文区", "宣武区", "朝阳区", "海淀区", "丰台区", "石景山区", "门头沟区",
                            "房山区", "通州区", "顺义区", "大兴区", "昌平区", "平谷区", "怀柔区", "密云县", "延庆县"}
            },
            {//天津
                    {"和平区", "河东区", "河西区", "南开区", "河北区", "红桥区", "塘沽区", "汉沽区", "大港区", "东丽区","北辰区"}
            },
            {//上海
                    {"长宁区", "静安区", "普陀区", "闸北区", "虹口区"}
            },
            {//广东
                    {"海珠区", "荔湾区", "越秀区", "白云区", "萝岗区", "天河区", "黄埔区", "花都区", "从化市", "增城市", "番禺区", "南沙区"}, //广州
                    {"宝安区", "福田区", "龙岗区", "罗湖区", "南山区", "盐田区"}, //深圳
                    {"武江区", "浈江区", "曲江区", "乐昌市", "南雄市", "始兴县", "仁化县", "翁源县", "新丰县", "乳源县"},//韶关
                    {"无"}
            },
            {//河南
                    {"中原区","二七区","管城区","金水区","上街区","惠济区","巩义市","荥阳市","新密市","新郑市" ,"登封市","中牟县" },
                    {"鼓楼区","龙亭区","顺河区","禹王台","金明区", "杞县","通许县", "尉氏县", "开封县" ,"兰考县"},
                    {"西工区","老城区","瀍河区","涧西区","吉利区","洛龙区","偃师市","孟津县","新安县","栾川县","嵩县","汝阳县", "宜阳县","洛宁县","伊川县"},
                    {"新华区","卫东区","湛河区","石龙区","舞钢市","汝州市","宝丰县","叶 县","鲁山县","郏县"},
                    {"魏都区","禹州市","长葛市","许昌县","鄢陵县", "襄城县"}
            },
    };
    //private LocationClient mLocationClient;//定位SDK的核心类
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    LinearLayout linearLayout_title,linearLayout_item;
    Button btn_start;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.startaddress, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

       /* mLocationClient = ((LocationApplication)getActivity().getApplication()).mLocationClient;
        InitLocation();
        mLocationClient.start();*/
    }

    /*private void InitLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setCoorType("bd09ll");//设置百度经纬度坐标系格式
        option.setScanSpan(1000);//设置发起定位请求的间隔时间为1000ms
        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
        mLocationClient.setLocOption(option);
        //Toast.makeText(getActivity(),"当前位置信息为",Toast.LENGTH_SHORT).show();
    }*/
    private void startLocate() {

        mLocationClient = new LocationClient(getActivity().getApplicationContext());     //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        //开启定位
        mLocationClient.start();

    }


    @Override
    public void onStart() {
        startLocate();
        super.onStart();
        /*linearLayout_title=getActivity().findViewById(R.id.city_all_title);
        linearLayout_item=getActivity().findViewById(R.id.city_item);
        for(int i=0;i<5;i++){
            TextView textView=new TextView(getContext());
            textView.setText(arr_province[i]);
            linearLayout_title.addView(textView);
            for(int j=0;j<arr_city[i].length;j++){
                //Log.v("");
                TextView textView1=new TextView(getContext());
                textView.setText(arr_city[i][j]);
                linearLayout_item.addView(textView1);
            }
        }*/
        btn_start=getActivity().findViewById(R.id.address_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),Route_optimization.class);
                startActivity(intent);
            }
        });

    }

    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);
            Log.v("----------------------",location.getCity());
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());// 单位：公里每小时
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                sb.append(location.getAltitude());// 单位：米
                sb.append("\ndirection : ");
                sb.append(location.getDirection());// 单位度
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());// 位置语义化信息
            List<Poi> list = location.getPoiList();// POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }
            Log.e("描述：", sb.toString());
        }

        /**
         实现位置的获取
         *
         */

    }
}
