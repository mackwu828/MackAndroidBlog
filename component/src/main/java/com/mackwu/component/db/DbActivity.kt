package com.mackwu.component.db

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 19:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class DbActivity : AppCompatActivity() {

    val dbHelper = DbHelper(this)
    val db = dbHelper.readableDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     * 插入数据
     */
    fun insert(){
        db.beginTransaction()
        db.execSQL("insert into " + dbHelper.name + " (Id, CustomName, OrderPrice, Country) values (1, 'Arc', 100, 'China')")
        db.execSQL("insert into " + dbHelper.name + " (Id, CustomName, OrderPrice, Country) values (2, 'Bor', 200, 'USA')")
        db.execSQL("insert into " + dbHelper.name + " (Id, CustomName, OrderPrice, Country) values (3, 'Cut', 500, 'Japan')")
        db.setTransactionSuccessful()
    }

    fun insert2(){
        val contentValues = ContentValues()
        contentValues.put("Id", 7)
        contentValues.put("CustomName", "Arc")
        contentValues.put("OrderPrice", 400)
        contentValues.put("OrderPrice", "China")
        db.insertOrThrow(dbHelper.name, null, contentValues)
        db.setTransactionSuccessful()
    }

}