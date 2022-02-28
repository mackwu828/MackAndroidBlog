package com.mackwu.component.ui;

import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.bean.Student;
import com.mackwu.component.core.backup.SdcardUtil;
import com.mackwu.component.core.db.DbManager;
import com.mackwu.component.databinding.ActivityDatabaseBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * ===================================================
 * Created by MackWu on 2021/12/24 14:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DatabaseActivity extends BaseActivity<BaseViewModel, ActivityDatabaseBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

        /*
         * getFilesDir()==/data/user/0/com.mackwu.component/files
         * getFilesDir()==/data/user/0/com.mackwu.component/cache
         * getExternalFilesDir()==/storage/emulated/0/Android/data/com.mackwu.component/files
         * getExternalCacheDir()==/storage/emulated/0/Android/data/com.mackwu.component/cache
         * Environment.getExternalStorageState()==mounted
         * Environment.getExternalStorageDirectory()==/storage/emulated/0
         * Environment.getExternalStoragePublicDirectory()==/storage/emulated/0/Music
         */
        LogUtil.d(" " + "\n" +
                "getFilesDir()==" + getFilesDir() + "\n" +
                "getFilesDir()==" + getCacheDir() + "\n" +
                "getExternalFilesDir()==" + getExternalFilesDir(null) + "\n" +
                "getExternalCacheDir()==" + getExternalCacheDir() + "\n" +
                "Environment.getExternalStorageState()==" + Environment.getExternalStorageState() + "\n" +
                "Environment.getExternalStorageDirectory()==" + Environment.getExternalStorageDirectory() + "\n" +
                "Environment.getExternalStoragePublicDirectory()==" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "\n" +
                "isBuildInSdCardMounted==" + SdcardUtil.isBuildInSdCardMounted() + "\n" +
                "getBuildInSdcardRootPath==" + SdcardUtil.getBuildInSdcardRootPath() + "\n"
        );


        binding.btnInsert.setOnClickListener(v -> {
            LogUtil.d("btnInsert...");
            List<Student> students = new ArrayList<>();
            students.add(new Student(1, "张三", 22));
            students.add(new Student(2, "李四", 34));
            students.add(new Student(3, "王五", 18));
            students.add(new Student(4, "吴天", 19));
            DbManager.getInstance().insertOrReplaceInTx(students);
        });
        binding.btnDelete.setOnClickListener(v -> {
        });
        binding.btnUpdate.setOnClickListener(v -> {
        });
        binding.btnQuery.setOnClickListener(v -> {
            LogUtil.d("btnQuery...");
            List<Student> students = DbManager.getInstance().queryAll();
            for (Student student : students) {
                LogUtil.d(student.toString());
            }
        });
    }

}
