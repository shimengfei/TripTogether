package com.hebut.triptogether.UI.Route;

import android.util.Log;

import com.baidu.mapapi.model.LatLng;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;

/**
 *
 * 获取位置经纬度方法
 * Created by LiXiaoTong on 2017/6/28.
 */
public class LngAndLatUtil {
    public static LatLng getLngAndLat(String address){
        String map="";
        LatLng latLng=null;
        String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=S17HPt9cd0fppjVQpKlUXX8mpGUK6G2G";
        try {
            String json = loadJSON(url);
            JSONObject obj = JSONObject.fromObject(json);

            if(obj.get("status").toString().equals("0")){
                double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
                double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
                map= getDecimal(lng)+","+getDecimal(lat);
                latLng=new LatLng(lat,lng);

                Log.v("位置信息","经度："+lng+"维度"+lat);
                //System.out.println("经度："+lng+"---纬度："+lat);
            }else{
                Log.v("","未找到相匹配的经纬度！");
                //System.out.println("未找到相匹配的经纬度！");
            }
        }catch (Exception e){
            Log.v("","未找到相匹配的经纬度！");
        }
        return latLng;
    }

    public static double getDecimal(double num) {
        if (Double.isNaN(num)) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(num);
        num = bd.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        return num;
    }

    public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream(),"UTF-8"));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }
    public static double getLatLngDistance(LatLng start, LatLng end){
        //自己实现距离算法：
        /**
         * 计算两点之间距离
         * @param start
         * @param end
         * @return String  多少m ,  多少km
         */

        double lat1 = (Math.PI/180)*start.latitude;
        double lat2 = (Math.PI/180)*end.latitude;

        double lon1 = (Math.PI/180)*start.longitude;
        double lon2 = (Math.PI/180)*end.longitude;

//       double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);
//       double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);
//       double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);
//       double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);

        //地球半径
        double R = 6371.004;

        //两点间距离 m，如果想要米的话，结果*1000就可以了
        double dis =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;
        NumberFormat nFormat = NumberFormat.getNumberInstance();  //数字格式化对象
        if(dis < 1){               //当小于1千米的时候用,用米做单位保留一位小数

            nFormat.setMaximumFractionDigits(1);    //已可以设置为0，这样跟百度地图APP中计算的一样
            dis *= 1000;

            return dis;
        }else{
            nFormat.setMaximumFractionDigits(2);
            return dis;
        }



    }

}
