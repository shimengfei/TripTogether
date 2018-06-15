package com.hebut.triptogether.UI.Route;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ListFragment;

import com.baidu.mapapi.model.LatLng;
import com.hebut.triptogether.Model.route;
import com.hebut.triptogether.R;
import com.hebut.triptogether.SQLiteDAo.routeDAO;

import java.io.IOException;
import java.util.List;

public class Route extends ListFragment implements View.OnClickListener, View.OnLongClickListener, AdapterView.OnItemLongClickListener {
    private AnimalListAdapter adapter = null;
    private Button btn_add,btn_form;
    private TextView tv_ms_content,tv_ms_time;
    private ListView lv_route,list;
    //private List<route> list;
    private routeDAO dao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new AnimalListAdapter (getActivity());
        setListAdapter(adapter);
        list=getActivity().findViewById(R.id.list);
        list.setLongClickable(true);
        list.setOnItemLongClickListener(this);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(true){
            return inflater.inflate(R.layout.activity_route, container, false);
        }else{
            return inflater.inflate(R.layout.activity_routenull,container,false);
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
         List<route> list;
        routeDAO route;
        route=new routeDAO(getContext());
        list=route.queryAll();
        String address[]=list.get(position).getName().split("-");
        LatLng[] latLngs=new LatLng[address.length];
        double[][] distance=new double[address.length][address.length];
        for(int i=0;i<address.length;i++){
            latLngs[i]=LngAndLatUtil.getLngAndLat(address[i]);
        }
        /*LatLng latLng1= LngAndLatUtil.getLngAndLat(address[0]);
        LatLng latLng2=LngAndLatUtil.getLngAndLat(address[1]);*/
        for(int i=0;i<address.length;i++){
            for(int j=0;j<address.length;j++){
                if(i!=j){
                    distance[i][j]=LngAndLatUtil.getLatLngDistance(latLngs[i],latLngs[j]);
                }else{
                    distance[i][j]=1000000;
                }
            }
        }
        int []BestRoute=new int[address.length];
        Optimization optimization=new Optimization(address.length, 40, 400, 250.0f, 0.98f,distance);
        try {
            optimization.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BestRoute=optimization.solve();
        String city="";
        Log.v("最优路径为：","");
        for(int i=0;i<BestRoute.length;i++){
            Log.v("",String.valueOf(BestRoute[i]));
            city+="->"+address[BestRoute[i]];
        }
        Log.v("旅游路线",city);
        new AlertDialog.Builder(getContext())
                .setTitle("建议行程")
                .setMessage(city+"\n"+"总行程长度:"+optimization.bestEvaluation+"\n"+"估计花费为："+optimization.money+"\n"+"建议游玩时间为："+optimization.day)
                .setPositiveButton("确定", null)
                .show();
        //Toast.makeText(getActivity(),BestRoute.toString(),Toast.LENGTH_SHORT).show();

        super.onListItemClick(l, v, position, id);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_add=getActivity().findViewById(R.id.route_add);
        //btn_form=getActivity().findViewById(R.id.route_form);
        btn_add.setOnClickListener(this);
        //btn_form.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.route_add:
                Intent intent = new Intent(getActivity(),Route_add.class);
                startActivity(intent);
                break;
            /*case R.id.route_form:
                //执行最有路径的规划
                Intent intent1=new Intent(getActivity(),Route_optimization.class);
                startActivity(intent1);
                break;*/
        }
    }


    @Override
    public boolean onLongClick(View view) {
        Toast.makeText(getActivity(),view.getId()+"长按",Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getActivity(),view.getId()+"长按",Toast.LENGTH_SHORT).show();
        return true;
    }
}
