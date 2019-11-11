package com.mackwu.component.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 18:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class DbHelper(
        val context: Context,
        val name: String = "test.db",
        val version: Int = 1
): SQLiteOpenHelper(context, name, null, version) {

    /**
     * 创建表
     */
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "create table if not exists $name (Id integer primary key, CustomName text, OrderPrice integer, Country text)"
        db.execSQL(sql)
    }

    /**
     * 更新表
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS $name"
        db.execSQL(sql)
        onCreate(db)
    }
}