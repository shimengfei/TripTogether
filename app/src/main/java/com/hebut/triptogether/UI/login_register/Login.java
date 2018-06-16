package com.hebut.triptogether.UI.login_register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.ab.util.AbStrUtil;
import com.ab.util.AbToastUtil;
import com.hebut.triptogether.Model.User;
import com.hebut.triptogether.UI.Main.MainActivity;
import com.hebut.triptogether.R;
import com.hebut.triptogether.Jsonutil.jsontool;
import com.hebut.triptogether.Jsonutil.jsonutil;
import com.hebut.triptogether.Model.person;
import com.hebut.triptogether.Jsonutil.*;
import android.os.Handler;
import net.sf.json.JSONObject;

import java.io.File;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button btn_login, btn_regist, btn_findback;
    private EditText editText_username, editText_pwd;
    private CheckBox checkBox_remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy(new
                    StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        }
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
                final String user = editText_username.getText().toString();
                final String pwd = editText_pwd.getText().toString();
                //mStrName = userName.getText().toString();
                //mStrPwd = userPwd.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    AbToastUtil.showToast(Login.this,R.string.error_name);
                    editText_username.setFocusable(true);
                    editText_username.requestFocus();
                    return;
                }

                if (!AbStrUtil.isNumberLetter(user)) {
                    AbToastUtil.showToast(Login.this,R.string.error_name_expr);
                    editText_username.setFocusable(true);
                    editText_username.requestFocus();
                    return;
                }

                if (AbStrUtil.strLength(user)<3) {
                    AbToastUtil.showToast(Login.this,R.string.error_name_length1);
                    editText_username.setFocusable(true);
                    editText_username.requestFocus();
                    return;
                }

                if (AbStrUtil.strLength(user)>20) {
                    AbToastUtil.showToast(Login.this,R.string.error_name_length2);
                    editText_username.setFocusable(true);
                    editText_username.requestFocus();
                    return;
                }

                if (AbStrUtil.isEmpty(pwd)) {
                    AbToastUtil.showToast(Login.this,R.string.error_pwd);
                    editText_pwd.setFocusable(true);
                    editText_pwd.requestFocus();
                    return;
                }

                if (AbStrUtil.strLength(pwd)<6) {
                    AbToastUtil.showToast(Login.this,R.string.error_pwd_length1);
                    editText_pwd.setFocusable(true);
                    editText_pwd.requestFocus();
                    return;
                }

                if (AbStrUtil.strLength(pwd)>20) {
                    AbToastUtil.showToast(Login.this,R.string.error_pwd_length2);
                    editText_pwd.setFocusable(true);
                    editText_pwd.requestFocus();
                    return;
                }
                // TODO Auto-generated method stub
               /* String path="http://192.168.130.2:8080/TripTogetherServer/jsonaction?test=person";
                String jsonstring= jsonutil.getjsoncontent(path);
                person person= jsontool.getperson(jsonstring);
                Log.i("Main", person.toString());
                editText_username.setText(String.valueOf(person.getId()));
                Toast.makeText(Login.this, jsonstring, Toast.LENGTH_SHORT).show();
                //查询数据库*/

                JSONObject jsonObject=jsontool.setUser(new User(user,pwd));
              PostThread postThread= new PostThread("/TripTogetherServer/jsonaction",jsonObject,this);
              postThread.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.v("消息传递结果",String.valueOf(getSign()));
/*                Handler handler=new Handler(){
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if(msg.what==1){
                            Intent intent=new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                        }else if(msg.what==0){
                            Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                };*/
              if(getSign()==0){
                  login(user,pwd);
                  Intent intent=new Intent(Login.this,MainActivity.class);
                  clearSign();
                  startActivity(intent);

              }else if(getSign()==0){
                  Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                  clearSign();
              }
              /*Log.v("消息传递",String.valueOf(postThread.Sign));
              if(postThread.Sign==0){
                  login(user,pwd);
                  Intent intent=new Intent(Login.this,MainActivity.class);
                  startActivity(intent);
              }else {
                  Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
              }*/
               /*@SuppressLint("HandlerLeak") android.os.Handler handler=new Handler(new Handler.Callback() {
                   @Override
                   public boolean handleMessage(Message message) {
                       switch (message.what){
                           case 0:
                               Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                               break;
                           case 1:
                               login(user,pwd);
                               Intent intent=new Intent(Login.this,MainActivity.class);
                               startActivity(intent);
                               break;
                               default:
                                   Toast.makeText(Login.this,"消息传递失败",Toast.LENGTH_SHORT).show();
                       }
                       return true;
                   }
               })
               {*/

                   /*public void handleMessage(Message msg) {
                       Log.v("消息",msg.toString());
                       switch (msg.what){
                           case 1:
                               login(user,pwd);
                               Intent intent=new Intent(Login.this,MainActivity.class);
                               startActivity(intent);
                               break;
                           case 0:
                               Toast.makeText(Login.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                               break;
                       }
                   }*/
               //};
               break;
            case R.id.registerBtn:
                //跳到注册页面
                Intent intent=new Intent(Login.this,register.class);
                startActivity(intent);
                Login.this.finish();
                break;
            case R.id.pwdBtn:
                //跳到找回密码页面
                Intent intent1=new Intent(Login.this,FindPwd.class);
                startActivity(intent1);

                break;
        }
    }
    public void clearSign() {
        //创建sharedPreference对象，info表示文件名，MODE_PRIVATE表示访问权限为私有的
        SharedPreferences sp = getSharedPreferences("sign", MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.clear();
        ed.commit();
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
    public int getSign() {
        //创建sharedPreference对象，info表示文件名，MODE_PRIVATE表示访问权限为私有的
        SharedPreferences sp = getSharedPreferences("sign", MODE_PRIVATE);

        int sign=sp.getInt("sign",0);
        return sign;
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