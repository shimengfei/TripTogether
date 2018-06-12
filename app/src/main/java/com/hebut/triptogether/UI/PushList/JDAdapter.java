package com.hebut.triptogether.UI.PushList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebut.triptogether.R;

import java.util.List;

public class JDAdapter extends ArrayAdapter<JD> {
    private int resourceID;
    public JDAdapter(Context context, int resource, List<JD> objects) {
        super(context, resource, objects);
        resourceID =resource;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        JD fruit = getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,null);
        TextView tv_id = (TextView) view.findViewById(R.id.tv1);
        TextView tv_name = (TextView) view.findViewById(R.id.tv2);
        TextView tv_price = (TextView) view.findViewById(R.id.tv3);
        ImageView imageView1 = (ImageView) view.findViewById(R.id.iv1);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.iv2);
        ImageView imageView3 = (ImageView) view.findViewById(R.id.iv3);
        tv_id.setText(fruit.getId()+"");
        tv_name.setText(fruit.getName());
        tv_price.setText(fruit.getBalance()+"");
        return view;
    }
}