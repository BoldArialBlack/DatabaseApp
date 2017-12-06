package com.example.asus.databaseapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends Activity {
    //定义Button，ListView，list数据集合，simpleAdapter适配器
    private Button create, updateDatabase, insert, updatebtn, query, delete;
    private ListView listview = null;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private SimpleAdapter simpleAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //取得组件
        create = (Button) findViewById(R.id.create);
        updateDatabase = (Button) findViewById(R.id.updateDatabase);
        insert = (Button) findViewById(R.id.insert);
        updatebtn = (Button) findViewById(R.id.update);
        query = (Button) findViewById(R.id.query);
        delete = (Button) findViewById(R.id.delete);
        listview = (ListView) findViewById(android.R.id.list);
        //绑定事件监听器
        create.setOnClickListener(new CreateListener());
        updateDatabase.setOnClickListener(new UpdateDatabaseListener());
        insert.setOnClickListener(new InsertListener());
        updatebtn.setOnClickListener(new UpdateListener());
        query.setOnClickListener(new QueryListener());
        delete.setOnClickListener(new DeleteListener());

        //适配器添加查询结果，并加到ListView中显示
        simpleAdapter = new SimpleAdapter(this, getData(), R.layout.list,
                new String[] { "_id", "name", "no" }, new int[] {
                R.id._id, R.id.name, R.id.credit });
        listview.setAdapter(simpleAdapter);

    }

    class CreateListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // 创建一个DatabaseHelper对象
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
                    "test_db");
            // 只有调用了DatabaseHelper对象的getReadableDatabase()方法，或者是getWritableDatabase()方法之后，才会创建，或打开一个数据库
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //适配器添加查询结果，并加到ListView中显示
            simpleAdapter = new SimpleAdapter(MainActivity.this, getData(), R.layout.list,
                    new String[] { "_id", "name", "no" }, new int[] {
                    R.id._id, R.id.name, R.id.credit });
            listview.setAdapter(simpleAdapter);
            db.close();
        }
    }

    class UpdateDatabaseListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
                    "test_db", 2);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //适配器添加查询结果，并加到ListView中显示
            simpleAdapter = new SimpleAdapter(MainActivity.this, getData(), R.layout.list,
                    new String[] { "_id", "name", "no" }, new int[] {
                    R.id._id, R.id.name, R.id.credit });
            listview.setAdapter(simpleAdapter);
            db.close();
        }
    }

    class InsertListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
                    "test_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //插入数据语句，以事务处理方式插入
            //主键不需要插入，会自己出现并自增
            db.beginTransaction(); //开始事务处理
            db.execSQL("insert into stu_info(sName,sNo) values('stu',2)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu1',3)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu2',4)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu3',5)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu4',6)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu5',7)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu6',8)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu7',9)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu8',10)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu9',11)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu10',12)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu11',13)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu12',14)");
            db.execSQL("insert into stu_info(sName,sNo) values('stu13',15)");
            db.setTransactionSuccessful();  //设置事务标志为成功，当结束事务时就会提交事务
            db.endTransaction();//结束事务
        }
    }

    // 更新操作就相当于执行SQL语句当中的update语句
    // UPDATE table_name SET XXCOL=XXX WHERE XXCOL=XX...
    class UpdateListener implements View.OnClickListener {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            // 得到一个可写的SQLiteDatabase对象
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
                    "test_db");
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //更新_id=5的数据
            db.execSQL("UPDATE stu_info SET sName='student3'WHERE _id=4");
//            db.execSQL("UPDATE stu_info SET sName='student3',sNo=5 WHERE _id=4");
            //适配器添加查询结果，并加到ListView中显示
            simpleAdapter = new SimpleAdapter(MainActivity.this, getData(), R.layout.list,
                    new String[] { "_id", "name", "no" }, new int[] {
                    R.id._id, R.id.name, R.id.credit });
            listview.setAdapter(simpleAdapter);
            db.close();
        }
    }

    class QueryListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            System.out.println("aaa------------------");
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
                    "test_db");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            //下面是查询语句
            Cursor cursor = db.rawQuery("select * from stu_info ",
                    null);
            while (cursor.moveToNext()) {    //获取游标下移，作为循环，从而获得所有数据
                String id = cursor.getString(0);  //获取_id
                String name = cursor.getString(1); //获取name
                String no = cursor.getString(2);//获取no
                System.out.println("query--->" + id + "," + name + "," + no);//输出数据
            }
            //适配器添加查询结果，并加到ListView中显示
            simpleAdapter = new SimpleAdapter(MainActivity.this, getData(), R.layout.list,
                    new String[] { "_id", "name", "no" }, new int[] {
                    R.id._id, R.id.name, R.id.credit });
            listview.setAdapter(simpleAdapter);
            db.close();
        }}
    class DeleteListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
                    "test_db");
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            db.execSQL("delete from stu_info where sNo=2");//删除 学号为2的数据
            //适配器添加查询结果，并加到ListView中显示
            simpleAdapter = new SimpleAdapter(MainActivity.this, getData(), R.layout.list,
                    new String[] { "_id", "name", "no" }, new int[] {
                    R.id._id, R.id.name, R.id.credit });
            listview.setAdapter(simpleAdapter);
            db.close();
        }

    }
    private List<Map<String, Object>> getData() {
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity.this,
                "test_db",2);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from stu_info",
                null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String no = cursor.getString(2);
            //这里同样是查询，只不过把查询到的数据添加到list集合，可以参照ListView的用法
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("_id", id); //获取_id
            map.put("name", name);	//获取name
            map.put("no", no); //获取credit学分
            list.add(map);
        }
        return list;

    }
}
