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
        switch (position%3){
            case 0:
                //tv_name.setText("杭州西湖它不仅是一个自然湖，更是一个人文湖，它是人类与自然和谐相处的产物，这种基因是无法复制的。西湖是“自然与人类共同的作品”，春来“花满苏堤柳满烟”，夏有“红衣绿扇映清波”，秋是“一色湖光万顷秋”，冬则“白堤一痕青花墨”。");
                imageView1.setImageResource(R.mipmap.xihu);
                break;
            case 1:
                //tv_name.setText("颐和园，中国清朝时期皇家园林，前身为清漪园，坐落在北京西郊，距城区十五公里，占地约二百九十公顷，与圆明园毗邻。");
                imageView1.setImageResource(R.mipmap.yiheyuan);
                break;
            case 2:
                //tv_name.setText("万里长城是新七大奇迹之一，1987年12月，长城被列为世界文化遗产。长城东西南北交错，绵延起伏于我们伟大祖国辽阔的土地上。");
                imageView1.setImageResource(R.mipmap.changcheng);
                default:
                    break;
        }

        return view;
    }
}