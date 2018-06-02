package com.hebut.triptogether;

import android.content.SharedPreferences;
import android.util.Base64;
import android.view.View;
import com.ab.activity.AbActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.util.Log;
public class User extends AbActivity {
    public void login(String name,String password) {
        //创建sharedPreference对象，info表示文件名，MODE_PRIVATE表示访问权限为私有的
        SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

        //获得sp的编辑器
        SharedPreferences.Editor ed = sp.edit();

        //以键值对的显示将用户名和密码保存到sp中
        ed.putString("username", name);
        ed.putString("password", password);

        //提交用户名和密码
        ed.commit();
    }
    //读取保存在本地的用户名和密码
    public String  readAccount() {

        //创建SharedPreferences对象
        try
        {
            File f=new File("info");

            if(!f.exists())
            {
                Log.v("判断文件是否存在","true");
                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                Log.v("错误定位","true");
                //获得sp的编辑器
                SharedPreferences.Editor ed = sp.edit();

                //以键值对的显示将用户名和密码保存到sp中
                ed.putString("username","");
                ed.putString("password","");
                return null;
            }else{
                Log.v("判断文件是否存在","false");
                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

                //获得保存在SharedPredPreferences中的用户名和密码
                if(sp!=null){//去查询数据库对应的用户表判断用户的登录是否合法
                    String username = sp.getString("username", "");
                    String password = sp.getString("password", "");
                    /*
                     * 数据库的查询操作
                     *
                     * */
                    return username;
                }else{
                    return null;

                }

            }

        }
        catch (Exception e)
        {
            return  null;
        }


    }

}
