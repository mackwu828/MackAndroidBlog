package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.bean.Student;
import com.mackwu.component.core.DbManager;
import com.mackwu.component.databinding.ActivitySqlBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * ===================================================
 * Created by MackWu on 2021/12/24 14:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SqlActivity extends BaseActivity<BaseViewModel, ActivitySqlBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnInsert.setOnClickListener(v -> {
            LogUtil.d("btnInsert...");
            List<Student> students = new ArrayList<>();
            students.add(new Student(1, "张三", 22));
            students.add(new Student(2, "李四", 34));
            students.add(new Student(3, "王五", 18));
            students.add(new Student(4, "吴天", 19));
            students.add(new Student(5, "润土", 43));
            DbManager.getInstance().insertInTx(students);
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
