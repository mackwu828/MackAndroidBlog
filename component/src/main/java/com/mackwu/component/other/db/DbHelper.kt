package com.mackwu.component.other.db

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
class DbHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    /**
     * 创建表
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_STUDENT)
//        db.execSQL(CREATE_COMMENT)
    }

    /**
     * 更新表
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_STUDENT)
        onCreate(db)
    }

    companion object {
        private const val DB_NAME = "test.db"
        private const val DB_VERSION = 1

        /**
         * table name
         */
        const val STUDENT_TABLE_NAME = "student"
        const val COMMENT_TABLE_NAME = "comment"

        /**
         * create table sql
         */
        private const val CREATE_STUDENT = "create table if not exists $STUDENT_TABLE_NAME (id integer primary key autoincrement, name text, age integer, sex text)"
        private const val CREATE_COMMENT = "create table if not exists $COMMENT_TABLE_NAME (id integer primary key autoincrement, content text)"

        /**
         * drop table sql
         */
        private const val DROP_STUDENT = "DROP TABLE IF EXISTS $STUDENT_TABLE_NAME"
        private const val DROP_COMMENT = "DROP TABLE IF EXISTS $COMMENT_TABLE_NAME"
    }

}