//package com.mackwu.component.func.database;
//
//import android.content.Context;
//import android.database.Cursor;
//
//import com.mackwu.base.util.Logger;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * ===================================================
// * Created by MackWu on 2021/12/24 15:26
// * <a href="mailto:wumengjiao828@163.com">Contact me</a>
// * <a href="https://github.com/mackwu828">Follow me</a>
// * ===================================================
// */
//public class GreenDaoManager {
//
//    private static volatile GreenDaoManager instance;
////    private DaoMaster daoMaster;
////    private DaoSession daoSession;
////    private StudentDao studentDao;
//
//    private GreenDaoManager() {
//    }
//
//    public static GreenDaoManager getInstance() {
//        if (instance == null) {
//            synchronized (GreenDaoManager.class) {
//                if (instance == null) {
//                    instance = new GreenDaoManager();
//                }
//            }
//        }
//        return instance;
//    }
//
//    public void setupDatabase(Context context) {
//        try {
//            GreenDaoOpenHelper openHelper = new GreenDaoOpenHelper(context, "component.db", null);
//            daoMaster = new DaoMaster(openHelper.getWritableDatabase());
//            daoSession = daoMaster.newSession();
//            studentDao = daoSession.getStudentDao();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 获取数据库版本号
//     */
//    public int getSchemaVersion() {
//        return daoMaster.getSchemaVersion();
//    }
//
//    /**
//     * 插入单个数据
//     * insert into student (id, name, age) values (1, '张三', 22)
//     */
//    public void insert() {
//        // 插入单个数据
////        studentDao.insert(student);
//        // 插入并替换单个数据
////        studentDao.insertOrReplace(student);
//
//        List<Student> students = new ArrayList<>();
//        students.add(new Student(11, "张恢复", 22, 1669262048000L, true));
//        students.add(new Student(12, "非法拘禁", 22, 1667896717000L));
//        students.add(new Student(13, "隔开", 22, 1668328717000L));
//        students.add(new Student(14, "李四", 29, 1667378317000L));
//
//        students.add(new Student(22, "祝福", 29, 1665650317000L, true));
//        students.add(new Student(23, "发", 18, 1666168717000L));
//
//        students.add(new Student(32, "奖金", 18, 1636015117000L));
//        students.add(new Student(33, "嗯嗯嗯", 18, 1637311117000L));
//        students.add(new Student(34, "ff", 30, 1637051917000L));
////        students.add(new Student(4, 14, "吴天", 19));
////        students.add(new Student(5, 11, "XXX", 29));
//
//        // 插入列表
////        studentDao.insertInTx(students);
//        // 插入并替换列表
//        studentDao.insertOrReplaceInTx(students);
//
////        String sql = "insert into student (id, name, age) values (1, '张三', 22)";
////        daoSession.getDatabase().execSQL(sql);
//    }
//
//    /**
//     * 删除数据
//     */
//    public void delete() {
////        studentDao.delete(student);
//    }
//
//    /**
//     * 更新数据
//     */
//    public void update() {
////        studentDao.update(student);
////        studentDao.updateInTx(students);
//    }
//
//    /**
//     * 查询数据
//     */
////    SELECT *
////    FROM STUDENT
////    LEFT JOIN (SELECT min(TIME_STAMP) createTime FROM STUDENT GROUP BY strftime('%Y', TIME_STAMP/1000, 'unixepoch')) a
////    ON STUDENT.TIME_STAMP = a.createTime
////    GROUP BY strftime('%Y', TIME_STAMP/1000, 'unixepoch')
//    public void query() {
//        Logger.d("query...");
//        // 查询所有数据
//        List<Student> students = studentDao.queryBuilder().list();
//
//        // 查询数量
////        QueryBuilder<Student> where = studentDao.queryBuilder()
////                .where(StudentDao.Properties.Age.eq(22));
////        long count = where.count();
//
//        for (Student student : students) {
//            Logger.d(student.toString());
//        }
//    }
//
//    /**
//     * 按年分组。
//     * SELECT * from Student GROUP BY strftime('%Y', TIME_STAMP/1000, 'unixepoch')
//     * <p>
//     * SELECT * from Student
//     * where TIME_STAMP in (select max(TIME_STAMP) from STUDENT where VISIBLE=1 group by strftime('%Y', TIME_STAMP/1000, 'unixepoch'))
//     * order by TIME_STAMP desc
//     */
//    public void a() {
//        // studentDao.queryRaw默认添加了Select * from 表名
//        String groupBy = " group by strftime('%Y', TIME_STAMP/1000, 'unixepoch') ";
//
//        String where = "where TIME_STAMP " +
//                "in (select max(TIME_STAMP) from STUDENT where VISIBLE=1 group by strftime('%Y', TIME_STAMP/1000, 'unixepoch')) " +
//                "order by TIME_STAMP desc";
//        List<Student> students = studentDao.queryRaw(where);
//
//        for (Student student : students) {
//            Logger.d(student.toString());
//        }
//    }
//
//    /**
//     * 查询数据的下标
//     */
//    private long getPageIndex() {
//        String sql = "SELECT " +
//                "(SELECT count(b._id) from STUDENT as b where TIME_STAMP >= 1616579111000)" +
//                "FROM STUDENT";
//        Cursor c = studentDao.getDatabase().rawQuery(sql, null);
//        long index = 0;
//        if (c.moveToFirst()) {
//            index = c.getLong(0) - 1;
//        }
//        c.close();
//        Logger.d("index=" + index);
//        return index;
//    }
//
//}
