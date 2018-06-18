package com.hebut.triptogether.Jsonutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import net.sf.json.JSONObject;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.os.Handler;
import android.widget.Toast;

import static android.content.Context.MODE_MULTI_PROCESS;
import static android.content.Context.MODE_PRIVATE;

public class PostThread extends Thread {
    private String path;
    private JSONObject jsonObject;
    public int Sign=1;

    private Context activity;
    public PostThread() {


    }

    public PostThread(String path, JSONObject jsonObject,Context activity) {
        this.path = path;
        this.jsonObject = jsonObject;
        //Log.v("json初始化",jsonObject.toString());
        this.activity=activity;
    }
    public void login(int sign) {
        //创建sharedPreference对象，info表示文件名，MODE_PRIVATE表示访问权限为私有的
        SharedPreferences sp = activity.getSharedPreferences("sign", MODE_MULTI_PROCESS);

        //获得sp的编辑器
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("sign", sign);
        ed.commit();
    }

    public void clearSign() {
        File file= new File("/data/data/"+activity.getPackageName().toString()+"/shared_prefs","sign.xml");
        if(file.exists())
        {
            file.delete();
        }
        //创建sharedPreference对象，info表示文件名，MODE_PRIVATE表示访问权限为私有的
        SharedPreferences sp = activity.getSharedPreferences("sign", MODE_MULTI_PROCESS);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.commit();
    }
    public void run() {
        clearSign();
        Looper.prepare();
        HttpURLConnection conn = null;
        String urlStr = "http://d2115564y6.iok.la:10980" + path;
        InputStream is = null;
        String resultData = "";
        BufferedReader reader;
        try {
            URL url = new URL(urlStr); //URL对象
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
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String result = reader.readLine();
                if(result.equals("error")){
                    login(0);
                }else if(result.equals(null)){

                }else {
                   login(1);
                }

                /*if(result=="error"){
                    Message message=new Message();
                    message.what=0;
                    Handler handler=new Handler();
                    handler.sendMessage(message);
                    Sign=1;
                }else {
                    Message message=new Message();
                    message.what=1;
                    Handler handler=new Handler();
                    handler.sendMessage(message);

                    Sign=0;
                    Log.v("服务器返回结果",String.valueOf(Sign));
                }*/
                Log.v("服务器传回数据",result);
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally
        {

            }
        }
    }
