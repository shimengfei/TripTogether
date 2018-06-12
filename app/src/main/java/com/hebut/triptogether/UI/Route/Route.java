package com.hebut.triptogether.UI.Route;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ListView;

import com.hebut.triptogether.R;

public class Route extends Fragment implements View.OnClickListener {
    private Button btn_add,btn_form;
    private ListView lv_route;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(true){
            return inflater.inflate(R.layout.activity_route, container, false);
        }else{
            return inflater.inflate(R.layout.activity_routenull,container,false);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_add=getActivity().findViewById(R.id.route_add);
        btn_form=getActivity().findViewById(R.id.route_form);
        btn_add.setOnClickListener(this);
        btn_form.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.route_add:
                Intent intent = new Intent(getActivity(),Route_add.class);
                startActivity(intent);
                break;
            case R.id.route_form:
                //执行最有路径的规划
                Intent intent1=new Intent(getActivity(),Route_optimization.class);
                startActivity(intent1);
                break;
        }
    }
}
