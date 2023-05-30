package com.mackwu.component.func.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import org.greenrobot.greendao.database.Database;

/**
 * ===================================================
 * Created by MackWu on 2022/1/5 10:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db, false);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"STUDENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"STUDENT_ID\" INTEGER NOT NULL UNIQUE ," + // 1: studentId
                "\"NAME\" TEXT," + // 2: name
                "\"AGE\" INTEGER NOT NULL ," + // 3: age
                "\"TIME_STAMP\" INTEGER NOT NULL ," + // 4: timeStamp
                "\"VISIBLE\" INTEGER NOT NULL );"); // 5: visible
    }

}
