package com.hebut.triptogether.UI.PersonalInfor;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.hebut.triptogether.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class PersonalInfoActivity extends AppCompatActivity {

    public class Person{
        String name;
        String sex;
        String job;
        String hobby;
        String date;
        Person(String n,String s,String j,String h, String d){
            name=n;
            sex=s;
            job=j;
            hobby=h;
            date=d;
        }
        public String getName(){
            return this.name;
        }
        public String getSex(){
            return this.sex;
        }
        public String getJob(){
            return this.job;
        }
        public String getHobby(){
            return this.hobby;
        }
        public String getDate(){
            return this.date;
        }
    }
    private Button  submit  = null;
    URL url = null;
    String urlPath = "http://10.0.3.2:8080/XMLParse/AcceptJsonServlet";
    EditText name    = null;
    EditText date    = null;
    EditText address = null;
    EditText hobby = null;
    EditText job = null;
    RadioGroup sex=null;
    String selectSex=null;
    static Handler handler;

    public PersonalInfoActivity() {
         handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                if (msg.what == 0x123)
                {

                    Toast.makeText(PersonalInfoActivity.this,
                            "发送成功", Toast.LENGTH_LONG)
                            .show();

                }
                else
                {
                    Toast.makeText(PersonalInfoActivity.this,
                            "发送失败", Toast.LENGTH_LONG)
                            .show();

                }
            }
        };
    }

    private void selectRadioBtn(){
        RadioButton radioButton;
        radioButton = (RadioButton)findViewById(sex.getCheckedRadioButtonId());
        selectSex = radioButton.getText().toString();
    }
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_personalinfo);
            name = ((EditText) findViewById(R.id.name));

            date = ((EditText) findViewById(R.id.date));

            submit = (Button) findViewById(R.id.submit);
            job = (EditText) findViewById(R.id.job);
            hobby = (EditText) findViewById(R.id.hobby);
            address= (EditText) findViewById(R.id.address);
            sex=(RadioGroup)findViewById(R.id.sex);
            sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    selectRadioBtn();
                }
            });

            submit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    new Thread()
                    {
                        public void run()
                        {
                            JSONObject js = new JSONObject();

                            JSONObject params = new JSONObject();

                            // JSONArray array = new JSONArray();

                            Person p = new Person(name.getText().toString()
                                    ,selectSex
                                    ,job.getText().toString()
                                    ,hobby.getText().toString()
                                    ,address.getText().toString());
                            // 封装子对象
                            try
                            {
                                js.put("name", p.getName());
                                js.put("sex", p.getSex());
                                js.put("job", p.getJob());
                                js.put("hobby", p.getHobby());
                                js.put("date", p.getDate());
                                // 封装Person数组
                                params.put("Person", js);
                            }
                            catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                            // 把Json数据转换成String类型，使用输出流向服务器写
                            final String content = String.valueOf(params);

                            try
                            {
                                url = new URL(urlPath);
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setConnectTimeout(5000);
                                conn.setDoOutput(true);// 设置允许输出
                                conn.setRequestMethod("POST");
                                conn.setRequestProperty("Content-Type",
                                        "application/json; charset=UTF-8"); // 内容类型
                                OutputStream os = conn.getOutputStream();
                                os.write(content.getBytes());
                                os.close();
                                if (conn.getResponseCode() == 200)
                                {
                                    handler.sendEmptyMessage(0x123);
                                }
                                else
                                {
                                    handler.sendEmptyMessage(0x122);
                                }

                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }

                    }.start();

                }
            });

        }
    }

