package com.hebut.triptogether.UI.PushList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.hebut.triptogether.Model.JD;
import com.hebut.triptogether.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment {

    private List<JD> list;
    private ListView listView;
    private  JD f;
    private SearchView search;
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
       client.get("http://d2115564y6.iok.la:10980/TripTogetherServer/push", null, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                try {

                   //Toast.makeText(getActivity(),new String(bytes),Toast.LENGTH_SHORT).show();
                   JSONObject jsonObject=JSONObject.fromObject(new String(bytes));
                    JSONArray jsonArray = jsonObject.getJSONArray("obj");//获取数据集名称为obj的数据
                    Log.d("jsonArray数据输出：", String.valueOf(jsonArray));
                    for (int j = 0; j < jsonArray.size();j++) {
                        JD novels = JD.sectionInfoData(jsonArray.getJSONObject(j));//把数据存在novels集合中
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
                Log.v("返回代码：",String.valueOf(i));
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
            String path= basSectionInfo.getHeadPortrait();
            Context ctx=getContext();
            int resId = getResources().getIdentifier(path, "mipmap" , ctx.getPackageName());
            Resources res = getResources();
            Bitmap bm= BitmapFactory.decodeResource(res, resId);
            //00000000000000000000000000000000000000000000000000000000

            sectionInfo.jd_lv_headPortrait.setImageBitmap(bm);//不会变形
            sectionInfo.jd_tv_jdName.setText(basSectionInfo.getJdName());
            sectionInfo.jd_tv_sectionPrice.setText("￥"+basSectionInfo.getPrice());
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
        search=(SearchView)getActivity().findViewById(R.id.search);
        // 设置该SearchView显示搜索按钮
        search.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        search.setQueryHint("查找");
        listView.setTextFilterEnabled(true);//设置lv可以被过虑
        // 设置该SearchView默认是否自动缩小为图标
        search.setIconifiedByDefault(false);
        // 为该SearchView组件设置事件监听器
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 实际应用中应该在该方法内执行实际查询
                // 此处仅使用Toast显示用户输入的查询内容
                listView.setFilterText(s);
                //Toast.makeText(getActivity(), "您的选择是:" + s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //Toast.makeText(getActivity(), "textChange--->" + s, Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(s)) {
                    // 清除ListView的过滤
                    listView.clearTextFilter();
                } else {
                    // 使用用户输入的内容对ListView的列表项进行过滤
                    listView.setFilterText(s);
                }
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                JD   JD = (JD) adapterView.getItemAtPosition(i);
            }
        });
    }

}

