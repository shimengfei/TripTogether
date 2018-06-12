package com.hebut.triptogether.UI.Route;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hebut.triptogether.R;

import java.util.ArrayList;
import java.util.List;

public class MyListViewAdapter extends BaseAdapter {
    private Context context;
    private List<String> datas = new ArrayList<String>();

    public MyListViewAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        if (datas == null)
            return 0;
        return datas.size();
    }
    public void addData(String text){
        if(datas!=null){
            datas.add(text);
        }

    }
    public void delData() {
        if (datas != null && datas.size() > 0)
            datas.remove(datas.size() - 1);// 移除最后一条数据
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       return view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
