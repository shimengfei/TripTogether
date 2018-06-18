package com.hebut.triptogether.UI.PersonalInfor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hebut.triptogether.R;

public class PersonalCenterFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     return inflater.inflate(R.layout.peronalcenter, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView profile_name=getActivity().findViewById(R.id.profile_name);
        CircleImageView profile_image=getActivity().findViewById(R.id.profile_image);
        LSettingItem item_one=getActivity().findViewById(R.id.item_one);
        LSettingItem item_four=getActivity().findViewById(R.id.item_four);
        LSettingItem item_five=getActivity().findViewById(R.id.item_five);
        LSettingItem item_six=getActivity().findViewById(R.id.item_six);
        LSettingItem item_seven=getActivity().findViewById(R.id.item_seven);
        LSettingItem item_eight=getActivity().findViewById(R.id.item_eight);
        SharedPreferences sp=getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        profile_name.setText(sp.getString("username","获取用户名失败"));

        item_one.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                //Toast.makeText(getActivity(),"item_one",//Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), PersonalInfoActivity.class);
                getActivity().startActivity(intent);
            }
        });
        item_four.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                //Toast.makeText(getActivity(),"item_four",//Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), MoneyActivity.class);
                getActivity().startActivity(intent);
            }
        });
        item_five.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                //Toast.makeText(getActivity(),"item_five",//Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), PickOrTakeImageActivity.class);
                getActivity().startActivity(intent);
            }
        });
        item_six.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                //Toast.makeText(getActivity(),"item_six",//Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), HelpActivity.class);
                getActivity().startActivity(intent);
            }
        });
        item_seven.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                //Toast.makeText(getActivity(),"item_seven",//Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(intent);
            }
        });
        item_eight.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                //Toast.makeText(getActivity(),"item_eight",//Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getActivity(), SetActivity.class);
                getActivity().startActivity(intent);
            }
        });
        }
    }

