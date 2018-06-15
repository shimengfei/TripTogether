package com.hebut.triptogether.UI.PushList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.global.AbAppConfig;
import com.ab.http.entity.Header;

import com.hebut.triptogether.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment {

    private List<JD> list;
    private EditText eName;
    //private EditText eBalance;
    private ListView listView;
    private  JD f;
    private  ImageView search;
    List<JD> listBasSectionInfoData = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.showlayout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        findSectionData();//获取数据
        listView.setAdapter(baseAdapter);
        baseAdapter.notifyDataSetChanged();
    }

    public void findSectionData(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://192.168.1.194:8080/TripTogetherServer/route", null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                try {
                    JSONObject object =  new JSONObject(new String("responseBody"));//获取json数据
                    JSONArray jsonArray = object.getJSONArray("obj");//获取数据集名称为obj的数据
                    Log.d("jsonArray数据输出：", String.valueOf(jsonArray));
                    for (int j = 0; j < jsonArray.length();j++) {
                        JD novels = JD.sectionInfoData(jsonArray.getJSONObject(i));//把数据存在novels集合中
                        if (novels != null){
                            listBasSectionInfoData.add(novels);
                        }else {
                            Toast.makeText(getActivity(), "数据为空！", Toast.LENGTH_LONG).show();
                        }
                    }
//数据加载完毕，通知列表去更新
                    baseAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "数据请求失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
                //请求失败的回调处理
                Toast.makeText(getActivity(), "请链接网络，稍后重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return listBasSectionInfoData.size();
        }

        @Override
        public Object getItem(int position) {
            return listBasSectionInfoData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final SectionInfo sectionInfo;
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.list_item,null);
                sectionInfo = new SectionInfo();
                sectionInfo.jd_lv_headPortrait = (ImageView) convertView.findViewById(R.id.jd_lv_headPortrait);
                sectionInfo.jd_tv_jdName = (TextView) convertView.findViewById(R.id.jd_tv_jdName);
                sectionInfo.jd_tv_sectionPrice = (TextView) convertView.findViewById(R.id.jd_tv_sectionPrice);
                sectionInfo.jd_tv_sectionIntro = (TextView) convertView.findViewById(R.id.jd_tv_sectionIntro);
                convertView.setTag(sectionInfo);
            } else {
                sectionInfo = (SectionInfo) convertView.getTag();
            }
            final JD basSectionInfo = (JD) getItem(position);
            Log.d("novels数据：", String.valueOf(basSectionInfo));

            //0000000000000000000000000000000000000000000000000000000
            String path= Environment.getExternalStorageDirectory()+ File.separator+basSectionInfo.getHeadPortrait();
            Bitmap bm = BitmapFactory.decodeFile(path);
            //00000000000000000000000000000000000000000000000000000000

            sectionInfo.jd_lv_headPortrait.setImageBitmap(bm);//不会变形
            sectionInfo.jd_tv_jdName.setText(basSectionInfo.getJdName());
            sectionInfo.jd_tv_sectionPrice.setText(basSectionInfo.getPrice());
            sectionInfo.jd_tv_sectionIntro.setText(basSectionInfo.getIntro());
            return convertView;
        }

        class SectionInfo {
            ImageView jd_lv_headPortrait;
            TextView jd_tv_jdName;
            TextView jd_tv_sectionPrice;
            TextView jd_tv_sectionIntro;
        }
    };

    private void initView() {
        listView = (ListView) getActivity().findViewById(R.id.list);
        eName = (EditText) getActivity().findViewById(R.id.ed1);
        //eBalance = (EditText) getActivity().findViewById(R.id.ed2);
        search=(ImageView) getActivity().findViewById(R.id.search) ;
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(search);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        JD   JD = (JD) adapterView.getItemAtPosition(i);
            }
        });
    }
    public void search(View view){

    }

}

