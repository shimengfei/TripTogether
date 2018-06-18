package com.hebut.triptogether.UI.Route;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hebut.triptogether.Model.route;
import com.hebut.triptogether.R;
import com.hebut.triptogether.SQLiteDAo.routeDAO;

import java.util.List;

class ViewHolder {
    public ImageView animal;
    public TextView tv_ms_context;
    public TextView tv_ms_time;
    public ImageView speaker;
}

public class AnimalListAdapter extends BaseAdapter {
    private routeDAO route;
    private List<route> list;
    private LayoutInflater mInflater = null;
    public AnimalListAdapter(Context context){
        super();
        route=new routeDAO(context);
        list=route.queryAll();
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(list.isEmpty()){
            Log.v("数据库状态","数据库为空");
            return 0;
        }else {
            return list.size();
        }

    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_listview, null);
            holder.animal = (ImageView) convertView.findViewById(R.id.iv_icon_read_status);
            holder.tv_ms_context = (TextView) convertView.findViewById(R.id.tv_msg_content);
            holder.tv_ms_time = (TextView) convertView.findViewById(R.id.tv_msg_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.animal.setImageResource(R.mipmap.createtask_fill);
        holder.tv_ms_context.setText(list.get(position).getName());
        holder.tv_ms_time.setText(list.get(position).getTime().toString());
       /* holder.speaker.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println("Click on the speaker image on ListItem ");
            }
        });*/

        return convertView;
    }

}