package com.example.asus.databaseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by asus on 2017/11/29.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static int VERSION = 1;
    private String TABLE_NAME = "stu_info";

    //四个参数的构造方法
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    //三个参数
    public DatabaseHelper(Context context, String name,
                          int version){
        this(context, name, null, version);
    }
    //两个参数
    public DatabaseHelper(Context context, String name){
        this(context, name,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        System.out.println("create a Database");
        StringBuffer sBuffer = new StringBuffer();
        sBuffer.append("CREATE TABLE [" + TABLE_NAME  + "] (");    //创建表和定义表明
        sBuffer.append("[_id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");//设置_id为主键，不能为空与自增性质
        sBuffer.append("[sName] TEXT,");   //一个为name的列名，类型为text，这点不同于java中的String
        sBuffer.append("[sNo] INTEGER)");  //一个为no的列名，类型为INTEGER整型，代表学号
        db.execSQL(sBuffer.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        VERSION = 2;
        System.out.println("upgrade a Database");
    }
}
