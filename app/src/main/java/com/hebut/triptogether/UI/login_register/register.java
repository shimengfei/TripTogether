package com.hebut.triptogether.UI.login_register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.hebut.triptogether.UI.Main.MainActivity;
import com.hebut.triptogether.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    private ImageButton back,clear_name,clear_pwd,clear_pwd2,clear_email;
    private EditText et_name,et_pwd,et_pwd2,et_email;
    private Button bt_agreet,bt_regist;
    private CheckBox cb_regist;
    private int Sign=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Init();
        InitOnclick();
    }
    public void Init(){
        back=findViewById(R.id.register_back);
        clear_name=findViewById(R.id.clearName);
        clear_pwd=findViewById(R.id.clearPwd);
        clear_pwd2=findViewById(R.id.clearPwd2);
        clear_email=findViewById(R.id.clearEmail);
        et_name=findViewById(R.id.userName);
        et_pwd=findViewById(R.id.userPwd);
        et_pwd2=findViewById(R.id.userPwd2);
        et_email=findViewById(R.id.email);
        bt_agreet=findViewById(R.id.agreementBtn);
        bt_regist=findViewById(R.id.registerBtn);
        cb_regist=findViewById(R.id.register_check);

    }
    public void InitOnclick(){
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
        switch (view.getId()){
            case R.id.register_back:
                Intent intent=new Intent(register.this,Login.class);
                startActivity(intent);
                break;
            case R.id.registerBtn:
                //注册成功之后的操作
                if(cb_regist.isChecked()){

                    Intent intent1=new Intent(register.this,MainActivity.class);
                    startActivity(intent1);
                }else {
                    Toast.makeText(register.this,"未同意注册协议",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()){
            case R.id.email:
                if(b){

                }else{
                    if(isEmail(et_email.getText().toString())){

                    }else{
                        Toast.makeText(register.this,"邮箱格式不正确，请重新输入",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.userPwd2:
                if(!b){
                    if(!et_pwd.getText().toString().equals(et_pwd2.getText().toString())){
                        Toast.makeText(register.this,"两次密码输入不一致",Toast.LENGTH_SHORT);
                    }
                }
                break;
        }
    }
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }
}
