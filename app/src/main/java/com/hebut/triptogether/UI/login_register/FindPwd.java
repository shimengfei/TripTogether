package com.hebut.triptogether.UI.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ab.activity.AbActivity;
import com.hebut.triptogether.R;

public class FindPwd extends AbActivity implements View.OnClickListener {

    EditText et_name,et_email;
    Button bt_findPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_pwd);
    }
    public void Init(){
        this.et_name=findViewById(R.id.userName);
        this.et_email=findViewById(R.id.email);
        this.bt_findPwd=findViewById(R.id.findPwdBtn);
    }
    public void InitOnclick(){
        this.bt_findPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.findPwdBtn:

                //进行邮箱确认，修改密码操作
                Toast.makeText(FindPwd.this,"修改信息已发到您的邮箱，请查收",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(FindPwd.this,Login.class);
                startActivity(intent);
                break;
        }
    }
}
