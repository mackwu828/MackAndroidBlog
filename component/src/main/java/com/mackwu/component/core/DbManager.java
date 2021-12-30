package com.mackwu.component.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mackwu.component.bean.DaoMaster;
import com.mackwu.component.bean.DaoSession;
import com.mackwu.component.bean.Student;
import com.mackwu.component.bean.StudentDao;

import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/12/24 15:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DbManager {
    
    private static DbManager instance;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private StudentDao studentDao;

    private DbManager() {
    }
    
    public static DbManager getInstance() {
        if (instance == null) {
            synchronized (DbManager.class) {
                if (instance == null) {
                    instance = new DbManager();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        devOpenHelper = new DaoMaster.DevOpenHelper(context, "component.db", null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        studentDao = daoSession.getStudentDao();
    }

    public void insert(Student student) {
        studentDao.insert(student);
    }

    public void insertInTx(List<Student> students) {
        studentDao.insertInTx(students);
    }

    public void insertOrReplaceInTx(List<Student> students) {
        studentDao.insertOrReplaceInTx(students);
    }

    public void delete(Student student) {
        studentDao.delete(student);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public List<Student> queryAll() {
        return studentDao.queryBuilder().list();
    }

    public List<Student> queryByAge(String age) {
        return studentDao.queryBuilder().where(StudentDao.Properties.Age.eq(age)).build().list();
    }

}
