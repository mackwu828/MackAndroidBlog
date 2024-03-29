//package com.mackwu.component.func.database;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.github.yuweiguocn.library.greendao.MigrationHelper;
//import com.mackwu.component.dao.DaoMaster;
//import com.mackwu.component.dao.StudentDao;
//
//import org.greenrobot.greendao.database.Database;
//
//public class GreenDaoOpenHelper extends DaoMaster.OpenHelper {
//
//    public GreenDaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
//        super(context, name, factory);
//    }
//
//    @Override
//    public void onUpgrade(Database db, int oldVersion, int newVersion) {
//        //noinspection unchecked
//        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
//
//            @Override
//            public void onCreateAllTables(Database db, boolean ifNotExists) {
//                DaoMaster.createAllTables(db, ifNotExists);
//            }
//
//            @Override
//            public void onDropAllTables(Database db, boolean ifExists) {
//                DaoMaster.dropAllTables(db, ifExists);
//            }
//        }, StudentDao.class);
//    }
//
//}
