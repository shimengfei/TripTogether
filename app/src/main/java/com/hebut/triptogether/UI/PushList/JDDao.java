package com.hebut.triptogether.UI.PushList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hebut.triptogether.Model.route;
import com.hebut.triptogether.UI.PersonalInfor.DbHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang_Rui on 2018/6/11.
 */

public class JDDao {
    private DbHelper dbHelper;

    public JDDao(Context context) {
        dbHelper =new DbHelper(context);
    }
    public void insert(JD JD){
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put("name",JD.getName());
        values.put("balance",JD.getBalance());
        long id = db.insert("JD",null,values);
        JD.setId(id);
        db.close();
    }
    //根据id删除数据
    public  int delete(long id){
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        int count=db.delete("JD","id=?",new String[]{id+""});
        db.close();
        return count;
    }
    public List<JD> getAll(){
        SQLiteDatabase db =dbHelper.getReadableDatabase();
        Cursor c =db.query("JD",null,null,null,null,null,"balance DESC");
        List<JD> list =new ArrayList<>();
        while (c.moveToNext()){
            long id =c.getLong(c.getColumnIndex("id"));
            String name = c.getString(1);
            int balance =c.getInt(2);
            list.add(new JD(id,name,balance));
        }
        c.close();
        db.close();
        return list;
    }

    //搜索
    public List<JD> get(String name){
        SQLiteDatabase db =dbHelper.getReadableDatabase();
        Cursor c =db.query("JD",null, "name=?", new String[]{name+""},null,null,"id DESC");
        List<JD> list =new ArrayList<>();
        while (c.moveToNext()){
            long id =c.getLong(c.getColumnIndex("id"));
            String Name = c.getString(1);
            int balance =c.getInt(2);
            list.add(new JD(id,Name,balance));
        }
        c.close();
        db.close();
        return list;
    }
}
