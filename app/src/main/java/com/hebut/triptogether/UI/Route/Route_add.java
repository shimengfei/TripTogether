package com.hebut.triptogether.UI.Route;

import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.ab.activity.AbActivity;
import com.hebut.triptogether.Model.route;
import com.hebut.triptogether.R;
import com.hebut.triptogether.SQLiteDAo.routeDAO;
import com.hebut.triptogether.UI.Main.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Route_add extends AppCompatActivity {
    ImageButton search;
    EditText et_search;
    FloatingActionButton floatingActionButton;
    List<String> chose=new ArrayList<String>();
    //定义图标数组
    private int[] imageRes = {
            R.mipmap.tianjin,
            R.mipmap.address1,
            R.mipmap.address2,
            R.mipmap.address3,
            R.mipmap.address4,
            R.mipmap.address5,
            R.mipmap.address6,
            R.mipmap.address7,
            R.mipmap.address8,};

    /*//定义图标下方的名称数组
    private String[] name = {
            "O2O收款",
            "订单查询",
            "转账",
            "手机充值",
            "信用卡还款",
            "水电煤",
            "违章代缴",
            "快递查询",
            "更多"
    };*/
    List<String> list=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_add);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        search=findViewById(R.id.search);
        et_search=findViewById(R.id.et_search);
        floatingActionButton=findViewById(R.id.route_sure);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routeDAO routeDAO=new routeDAO(Route_add.this);
                Time t=new Time(); // or Time t=new Time("GMT+8"); 加上Time Zone资料。
                t.setToNow(); // 取得系统时间。
                int year = t.year;
                int month = t.month+1;
                int day = t.monthDay;
                String time=String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                String message=org.apache.commons.lang3.StringUtils.join(chose, "-");
                routeDAO.insert(new route(message,time));
                /*FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction ft=fm.beginTransaction();
                Route xx=new Route();
                ft.replace(R.id.fl_content,xx);
                ft.commit();*/
                Intent intent = new Intent(Route_add.this,MainActivity.class);
                intent.putExtra("userloginflag", 1);
                startActivity(intent);
                /*FragmentManager fragmentManager = getSupportFragmentManager();
                // 开启事务
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                android.support.v4.app.Fragment fragment = new Route();
                transaction.add(R.id.fl_content, fragment);*/
                /*Intent intent=new Intent(Route_add.this,Route.class);
                startActivity(intent);*/
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address=et_search.getText().toString();

            }
        });
        list=CityData.getSampleContactList();
        int length = list.size();
        //生成动态数组，并且转入数据
        //Log.v("数组大小",String.valueOf(length));
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", imageRes[i%9]);//添加图像资源的ID
            map.put("ItemText", list.get(i));//按序号做ItemText
            lstImageItem.add(map);
        }
        //生成适配器的ImageItem 与动态数组的元素相对应
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,//数据来源
                R.layout.item_gridview,//item的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.img_shoukuan, R.id.tv_city});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Toast.makeText(Route_add.this,list.get(position),Toast.LENGTH_LONG).show();*/
                chose.add(list.get(position));
            }
        });
    }
}
