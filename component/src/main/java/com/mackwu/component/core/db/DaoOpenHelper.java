package com.mackwu.component.core.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.mackwu.component.core.dao.DaoMaster;
import com.mackwu.component.core.dao.StudentDao;

import org.greenrobot.greendao.database.Database;

/**
 * ===================================================
 * Created by MackWu on 2022/1/20 16:14
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DaoOpenHelper extends DaoMaster.OpenHelper {

    public DaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //noinspection unchecked
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, StudentDao.class);
    }

}
