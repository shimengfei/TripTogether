package com.hebut.triptogether.Jsonutil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hebut.triptogether.Model.route;
import com.hebut.triptogether.SQLiteDAo.routeDAO;
import static android.content.Context.MODE_PRIVATE;

public  class jsonroute {
    JSONObject jsonObject;
    HttpURLConnection conn = null;
    String urlStr = "http://d2115564y6.iok.la:10980/TripTogetherServer/route";
    InputStream is = null;
    String resultData = "";
    JSONArray jsonArray;
    BufferedReader reader;
    URL url; //URL对象
    public void getRoute(Context context, String name){
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接,下面设置这个连接
            //conn.setRequestMethod("POST"); //使用POST请求
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept", "application/json");
            // 往服务器里面发送数据
            jsonObject=new JSONObject();
            jsonObject.put("Sign","get");
            jsonObject.put("name",name);
            //测试使用
            if (jsonObject != null) {
                byte[] writebytes = jsonObject.toString().getBytes();
                // 设置文件长度
                Log.v("JSon对象",jsonObject.toString());
                conn.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
                OutputStream outwritestream = conn.getOutputStream();
                outwritestream.write(jsonObject.toString().getBytes());
                outwritestream.flush();
                outwritestream.close();
            }
            if (conn.getResponseCode() == 200)
            {
                routeDAO r=new routeDAO(context);
                r.deleteAll();
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String result = reader.readLine();
                jsonArray=JSONArray.fromObject(result);
                routeDAO routeDAO=new routeDAO(context);

                if(jsonArray.size()>0){
                    for(int i=0;i<jsonArray.size();i++){
                        // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        JSONObject job = jsonArray.getJSONObject(i);
// 得到 每个对象中的属性值
                       /* Gson gson=new Gson();
                        route r=gson.fromJson(result,route.class);
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(r.getTime()));*/
                        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = (Date) sdf.parse(job.getString("time"));*/
                        //java.util.Date date;
                       /* SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd", Locale.CHINA);
                        Log.v("--------------------", sdf.format(job.get("time").toString()));*/
                        /*try
                        {
                            //System.out.println (sdf.parse (job.getString("time")));

                        }
                        catch (ParseException e)
                        {
                            e.printStackTrace();
                        }*/
                        //date=sdf.parse(job.getString("time"));
                        //GsonBuilder gsonBuilder=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
                       routeDAO.insert(new route(job.getString("name"), job.getString("time")));
                       //Log.v("打印",new java.util.Date(job.get("time").toString()).toString());
                       // Log.v("获取listview",job.toString());
                    }
                }


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
