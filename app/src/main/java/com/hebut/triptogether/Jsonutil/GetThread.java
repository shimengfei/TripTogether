package com.hebut.triptogether.Jsonutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

 public class GetThread extends Thread{
    private String name;
    private String password;
    private String path;
    public  GetThread(){

    }
    public GetThread(String name,String password,String path){
        this.name=name;
        this.password=password;
        this.path=path;
    }
    public void run(){
        HttpURLConnection conn=null;//声明连接对象
        String urlStr="http://192.168.130.2:8080"+path+"?name="+name+"&password="+password;
        InputStream is = null;
        String resultData = "";
        try {
            URL url = new URL(urlStr); //URL对象
            conn = (HttpURLConnection)url.openConnection(); //使用URL打开一个链接,下面设置这个连接
            conn.setRequestMethod("GET"); //使用get请求

            if(conn.getResponseCode()==200){//返回200表示连接成功
                is = conn.getInputStream(); //获取输入流
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferReader = new BufferedReader(isr);
                String inputLine  = "";
                while((inputLine = bufferReader.readLine()) != null){
                    resultData += inputLine + "\n";
                }
                System.out.println("get方法取回内容："+resultData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}