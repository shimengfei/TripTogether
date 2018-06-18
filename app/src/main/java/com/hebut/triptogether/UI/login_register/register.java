package com.hebut.triptogether.UI.login_register;

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
import android.widget.Toast;

import com.hebut.triptogether.Model.User;
import com.hebut.triptogether.UI.Main.MainActivity;
import com.hebut.triptogether.R;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.hebut.triptogether.Jsonutil.*;
public class register extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private ImageButton back, clear_name, clear_pwd, clear_pwd2, clear_email;
    private EditText et_name, et_pwd, et_pwd2, et_email;
    private Button bt_agreet, bt_regist;
    private CheckBox cb_regist;
    private int Sign = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Init();
        InitOnclick();
    }

    public void Init() {
        back = findViewById(R.id.register_back);
        clear_name = findViewById(R.id.clearName);
        clear_pwd = findViewById(R.id.clearPwd);
        clear_pwd2 = findViewById(R.id.clearPwd2);
        clear_email = findViewById(R.id.clearEmail);
        et_name = findViewById(R.id.userName);
        et_pwd = findViewById(R.id.userPwd);
        et_pwd2 = findViewById(R.id.userPwd2);
        et_email = findViewById(R.id.email);
        bt_agreet = findViewById(R.id.agreementBtn);
        bt_regist = findViewById(R.id.registerBtn);
        cb_regist = findViewById(R.id.register_check);

    }

    public void InitOnclick() {
        this.back.setOnClickListener(this);
        this.bt_regist.setOnClickListener(this);
        // this.bt_agreet.setOnClickListener(this);
        this.cb_regist.setOnClickListener(this);
        this.clear_name.setOnClickListener(this);
        this.clear_pwd.setOnClickListener(this);
        this.clear_pwd2.setOnClickListener(this);
        this.clear_email.setOnClickListener(this);
        /*
         * 下面是注册edittext 焦点监听事件
         *
         *  */
        this.et_email.setOnFocusChangeListener(this);
        this.et_name.setOnFocusChangeListener(this);
        et_pwd.setOnFocusChangeListener(this);
        et_pwd2.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_back:
                Intent intent = new Intent(register.this, Login.class);
                startActivity(intent);
                break;
            case R.id.registerBtn:
                //注册成功之后的操作
                if(isEmail(et_email.getText().toString())){
                    if(et_pwd.getText().toString().equals(et_pwd2.getText().toString())){

                        if (cb_regist.isChecked()) {
                            User user=new User(et_name.getText().toString(),et_pwd.getText().toString(),et_email.getText().toString());
                            isRegister("/TripTogetherServer/register",jsontool.setUser(user));
                        } else {                            Toast.makeText(register.this, "未同意注册协议", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(register.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(register.this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.email:
                if (b) {

                } else {
                    if (isEmail(et_email.getText().toString())) {

                    } else {
                        Toast.makeText(register.this, "邮箱格式不正确，请重新输入", Toast.LENGTH_SHORT).show();

                    }
                }
                break;
            case R.id.userPwd2:
                if (b) {

                }
                else {
                    if (!et_pwd.getText().toString().equals(et_pwd2.getText().toString())) {
                        Toast.makeText(register.this, "两次密码输入不一致", Toast.LENGTH_SHORT);

                    }else {

                    }
                }
                break;
                default:

                    break;
        }
    }

    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public void isRegister(String path, JSONObject jsonObject) {
        HttpURLConnection conn = null;
        String urlStr = "http://d2115564y6.iok.la:10980" + path;
        InputStream is = null;
        String resultData = "";
        BufferedReader reader;
        try {
            URL url = null; //URL对象
            try {
                url = new URL(urlStr);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                conn = (HttpURLConnection) url.openConnection(); //使用URL打开一个链接,下面设置这个连接
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                Log.v("JSon对象", jsonObject.toString());
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
                if (result.equals("false")) {
                    Toast.makeText(register.this,"注册失败，请重试",Toast.LENGTH_SHORT).show();
                } else if (result.equals("true")) {
                    Toast.makeText(register.this,"注册成功",Toast.LENGTH_SHORT).show();
                    login(jsonObject.getString("username"),jsonObject.getString("password"));
                    Intent intent=new Intent(register.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.v("返回代码",String.valueOf(conn.getResponseCode()));

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
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
}
