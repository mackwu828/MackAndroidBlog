package com.mackwu.component.func.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mackwu.component.dao.StudentDao;

/**
 * @author MackWu
 * @since 2023/3/22 17:18
 */
public class SqliteManager {

    SQLiteDatabase db;
    public static final String TABLE_NAME = "STUDENT";

    public void setupDatabase(Context context) {
        try {
            SQLiteOpenHelper helper = new MySQLiteOpenHelper(context, "component.db", null, 1);
            db = helper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        ContentValues values = new ContentValues();
        values.put("studentId", 11);
        values.put("name", "Jack");
        db.insert(TABLE_NAME, null, values);
    }


    @SuppressLint("Range")
    public void query() {
        Cursor cursor = db.rawQuery("select * from user where id=?", new String[]{"1"});

//        // 移动到第一行
//        cursor.moveToFirst();
//        for (int i = 0; i < cursor.getCount(); i++) {
//             long studentId = cursor.getLong(cursor.getColumnIndex(StudentDao.Properties.StudentId.columnName));
//            // 移动到下一行
//            cursor.moveToNext();
//        }
//        cursor.close();

        while (cursor.moveToNext()) {
            long studentId = cursor.getLong(cursor.getColumnIndex(StudentDao.Properties.StudentId.columnName));
        }
        cursor.close();


        // Cursor对象常用方法如下：
//        c.move( int offset); //以当前位置为参考,移动到指定行
//        c.moveToFirst();    //移动到第一行
//        c.moveToLast();     //移动到最后一行
//        c.moveToPosition( int position); //移动到指定行
//        c.moveToPrevious(); //移动到前一行
//        c.moveToNext();     //移动到下一行
//        c.isFirst();        //是否指向第一条
//        c.isLast();     //是否指向最后一条
//        c.isBeforeFirst();  //是否指向第一条之前
//        c.isAfterLast();    //是否指向最后一条之后
//        c.isNull( int columnIndex);  //指定列是否为空(列基数为0)
//        c.isClosed();       //游标是否已关闭
//        c.getCount();       //总数据项数
//        c.getPosition();    //返回当前游标所指向的行数
//        c.getColumnIndex(String columnName);//返回某列名对应的列索引值
//        c.getString( int columnIndex);   //返回当前行指定列的值

    }

}
