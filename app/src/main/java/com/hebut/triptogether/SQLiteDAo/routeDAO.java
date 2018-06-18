package com.hebut.triptogether.SQLiteDAo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;

import com.hebut.triptogether.Model.route;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class routeDAO {
    private MyHelper helper;
    public routeDAO(Context context){

        helper=new MyHelper(context);
    }
    public void insert(route account){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",account.getName());

            values.put("time", String.valueOf(account.getTime()));

        long id=db.insert("route",null,values);
        account.setId(id);
        db.close();
    }
    //根据id删除数据
    public int delete(long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("route","_id=?",new String[]{id+""});
        db.close();
        return count;
    }
    public void deleteAll(){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from route");
        db.close();
    }
    //更新数据库
    public int update(route account){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",account.getName());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            values.put("time",new SimpleDateFormat().format(account.getTime()));
        }
        int count=db.update("route",values,"_id=?",new String[]{
                account.getId()+""
        });
        db.close();
        return count;
    }
    //查询所有数据倒序排列
    public List<route> queryAll(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor c=db.query("route",null,null,null,null,null,null);
        List<route> list=new ArrayList<route>();
        while(c.moveToNext()){
            long id=c.getLong(c.getColumnIndex("_id"));
            String name=c.getString(1);
            String time=c.getString(2);
            list.add(new route(id,name,time));
        }
        c.close();
        db.close();
        return list;
    }
}
