package com.hebut.triptogether.UI.PersonalInfor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zhang_Rui on 2018/6/11.
 */

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "JD.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("OnCreate");
        db.execSQL("Create TABLE JD(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20), balance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("OnUpgrade");
    }
}