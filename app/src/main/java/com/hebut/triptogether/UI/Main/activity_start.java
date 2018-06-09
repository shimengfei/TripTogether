package com.hebut.triptogether.UI.Main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.hebut.triptogether.R;
import com.hebut.triptogether.UI.login_register.Login;

public class activity_start extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟六秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

                //获得保存在SharedPredPreferences中的用户名和密码
                   //去查询数据库对应的用户表判断用户的登录是否合法
                    String username = sp.getString("username", "");
                    String password = sp.getString("password", "");
                    int sign=0;
                    /*
                     * 数据库的查询操作
                     *
                     * */
                Intent mainIntent;
                Log.v("读取记录",username+password);
                if(sign==0){
                     mainIntent = new Intent(activity_start.this,
                            Login.class);
                }else{
                     mainIntent = new Intent(activity_start.this,
                            MainActivity.class);
                }

                activity_start.this.startActivity(mainIntent);
                activity_start.this.finish();
            }

        }, SPLASH_DISPLAY_LENGHT);
    }
}
