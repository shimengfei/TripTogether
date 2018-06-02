package com.hebut.triptogether;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;
import java.util.logging.Handler;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login, btn_regist, btn_findback;
    private EditText editText_username, editText_pwd;
    private CheckBox checkBox_remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        initOnclick();
    }

    private void initView() {
        //登录界面控件初始化
        this.btn_login = findViewById(R.id.loginBtn);
        this.btn_regist = findViewById(R.id.registerBtn);
        this.btn_findback = findViewById(R.id.pwdBtn);
        this.checkBox_remember = findViewById(R.id.login_check);
        this.editText_username = findViewById(R.id.userName);
        this.editText_pwd = findViewById(R.id.userPwd);
    }

    private void initOnclick() {
        btn_login.setOnClickListener(this);
        btn_regist.setOnClickListener(this);
        btn_findback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                String user = editText_username.getText().toString();
                String pwd = editText_pwd.getText().toString();
                //查询数据库
                if(true){
                    login(user,pwd);
                    Intent intent=new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.registerBtn:
                //跳到注册页面
                break;
            case R.id.pwdBtn:
                //跳到找回密码页面
                break;
        }
    }

    public void login(String name, String password) {
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
    public String readAccount() {

        //创建SharedPreferences对象
        try {
            File f = new File("info");

            if (!f.exists()) {
                Log.v("判断文件是否存在", "true");
                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);
                Log.v("错误定位", "true");
                //获得sp的编辑器
                SharedPreferences.Editor ed = sp.edit();

                //以键值对的显示将用户名和密码保存到sp中
                ed.putString("username", "");
                ed.putString("password", "");
                return null;
            } else {
                Log.v("判断文件是否存在", "false");
                SharedPreferences sp = getSharedPreferences("info", MODE_PRIVATE);

                //获得保存在SharedPredPreferences中的用户名和密码
                if (sp != null) {//去查询数据库对应的用户表判断用户的登录是否合法
                    String username = sp.getString("username", "");
                    String password = sp.getString("password", "");
                    /*
                     * 数据库的查询操作
                     *
                     * */
                    return username;
                } else {
                    return null;

                }

            }
        } catch (Exception e)
        {
            return  null;
        }
    }
}