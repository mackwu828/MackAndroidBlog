package com.mackwu.component.core.db;

import android.content.Context;

import com.mackwu.component.bean.Student;
import com.mackwu.component.core.dao.DaoMaster;
import com.mackwu.component.core.dao.DaoSession;
import com.mackwu.component.core.dao.StudentDao;

import org.greenrobot.greendao.query.CursorQuery;
import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

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
    private Context context;
    private DaoOpenHelper openHelper;
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
        this.context = context;
        openHelper = new DaoOpenHelper(context, "component.db", null);
        daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
        studentDao = daoSession.getStudentDao();
    }

    /**
     * 获取数据库版本号
     */
    public int getSchemaVersion() {
        return daoMaster.getSchemaVersion();
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

    public void update(List<Student> students) {
        studentDao.updateInTx(students);
    }

    public List<Student> queryAll() {
        return studentDao.queryBuilder().list();
    }

    public List<Student> queryByAge(String age) {
        return studentDao.queryBuilder()
                .where(StudentDao.Properties.Age.eq(age))
                .build()
                .list();
    }

}
