package com.hebut.triptogether.UI.PushList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.hebut.triptogether.R;

import java.util.List;

//import com.hebut.triptogether.R;

public class ShowFragment extends Fragment {

    private List<JD> list;
    private JDDao JDdao;
    private EditText eName;
    //private EditText eBalance;
    private ListView listView;
    private JDAdapter JDAdapter;
    private  JD f;
    private  ImageView search;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.showlayout, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        JDdao =new JDDao(this.getContext());
        list=JDdao.getAll();
        JDAdapter =new JDAdapter(this.getContext(),R.layout.list_item,list);
        listView.setAdapter(JDAdapter);
    }

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
//    public void add(View view){
//        String name =eName.getText().toString();
//        String balance = eBalance.getText().toString();
//        f =new JD(name,balance.equals("")?0:Integer.parseInt(balance));
//        JDdao.insert(f);
//        list.add(f);
//        JDAdapter.notifyDataSetChanged();
//    }
    public void search(View view){
        String name =eName.getText().toString();
        JDdao =new JDDao(this.getContext());
        list=JDdao.get(name);
        JDAdapter =new JDAdapter(this.getContext(),R.layout.list_item,list);
        listView.setAdapter(JDAdapter);
        JDAdapter.notifyDataSetChanged();
    }

}

