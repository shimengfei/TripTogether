package com.hebut.triptogether.UI.PersonalInfor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hebut.triptogether.R;

import java.util.List;

/**
 * Created by Zhang_Rui on 2018/6/12.
 */

public class MyImageAdapter extends RecyclerView.Adapter<MyImageAdapter.ViewHolder> {
    private List<MyImage> myImageList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View view){
            super(view);
            imageView=view.findViewById(R.id.image_view);
        }
    }

    public MyImageAdapter(List<MyImage> ImageList){
        myImageList=ImageList;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyImage myImage=myImageList.get(position);
        holder.imageView.setImageResource(myImage.getMyImageId());

    }

    @Override
    public int getItemCount() {
        return myImageList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
}
