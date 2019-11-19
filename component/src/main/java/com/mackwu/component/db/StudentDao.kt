package com.mackwu.component.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.mackwu.component.bean.Student

/**
 * ===================================================
 * Created by MackWu on 2019/11/13 17:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 增删改查
 */
object StudentDao {

    private lateinit var dbHelper: DbHelper
    private lateinit var db: SQLiteDatabase

    fun init(context: Context) {
        dbHelper = DbHelper(context)
        db = dbHelper.readableDatabase
    }

    /**
     * 插入数据。通过sql
     */
    fun rawInsert() {
        db.execSQL("insert into " + DbHelper.STUDENT_TABLE_NAME + " (name, age, sex) values ('Mack', 25, '男')")
        db.execSQL("insert into " + DbHelper.STUDENT_TABLE_NAME + " (name, age, sex) values ('Rico', 26, '男')")
        db.execSQL("insert into " + DbHelper.STUDENT_TABLE_NAME + " (name, age, sex) values ('Tom', 27, '男')")
    }


    /**
     * 插入数据。通过ContentValues
     *
     * public long insert(String table, String nullColumnHack, ContentValues values)
     * table: 表名
     * nullColumnHack: 通常都用不到，直接传null
     * values: ContentValues。相当于hashMap, ContentValues内部创建了一个hashMap，用来存储数据
     */
    fun insert() {
        val values = ContentValues()
        values.put("name", "Arc")
        values.put("age", 22)
        values.put("sex", "女")
        db.insert(DbHelper.STUDENT_TABLE_NAME, null, values)
    }


    /**
     * 删除数据
     *
     * public int delete(String table, String whereClause, String[] whereArgs)
     * table: 表名
     * whereClause: 指定删除哪些行。相当于sql语句的where
     * whereArgs: 指定删除哪些行。相当于sql语句的where
     */
    fun delete() {
        db.delete(DbHelper.STUDENT_TABLE_NAME, "name=?", arrayOf("Arc"))
        // 等同于delete from student where name=Arc;
    }

    /**
     * 修改数据
     *
     * public int update(String table, ContentValues values, String whereClause, String[] whereArgs)
     * table: 表名
     * values: ContentValues
     * whereClause、whereArgs: 指定修改哪些行。相当于sql语句的where
     */
    fun update() {
        val values = ContentValues()
        values.put("age", 26)
        db.update(DbHelper.STUDENT_TABLE_NAME, values, "name=?", arrayOf("Mack"))
        // 等同于update student set age=26 where name=Mack;
    }


    /**
     * 查询数据
     */
    fun rawQuery(){

    }

    /**
     * 查询数据。
     *
     * public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
     * table: 表名
     * columns: 查询哪一列，不指定默认查询所有列
     * selection、selectionArgs: 查询哪一行，不指定默认是查询所有行
     * groupBy: 指定需要去group by的列
     * having: 对group by之后的数据进行进一步的过滤
     * orderBy: 排序方式
     */
    fun query(): Cursor {
        // 只指定表名，表示查询所有的数据
        val cursor = db.query(DbHelper.STUDENT_TABLE_NAME, null, null, null, null, null, null)
//        cursor?.run {
//            val list = arrayListOf<Student>()
//            if (moveToFirst()) {
//                do {
//                    val id = getInt(getColumnIndex("id"))
//                    val name = getString(getColumnIndex("name"))
//                    val age = getInt(getColumnIndex("age"))
//                    val sex = getString(getColumnIndex("sex"))
//                    val student = Student(id, name, age, sex)
//                    list.add(student)
//                }while (moveToNext())
//            }
//            close()
//            for (student in list) {
//                Log.d("TAG", student.toString())
//            }
//        }
        return cursor
        // 查询年龄大于24岁的数据
//        db.query(DbHelper.STUDENT_TABLE_NAME, null, "age>?", arrayOf("24"), null, null, null)
    }

    fun queryMan(): Cursor{
        return db.query(DbHelper.STUDENT_TABLE_NAME, null, "sex=?", arrayOf("男"), null, null, null)
    }

}