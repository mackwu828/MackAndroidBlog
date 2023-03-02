package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.func.database.GreenDaoManager;
import com.mackwu.component.databinding.ActivityDatabaseBinding;


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
        binding.btnInsert.setOnClickListener(v -> {
            Logger.d("btnInsert...");
            GreenDaoManager.getInstance().insert();
        });
        binding.btnDelete.setOnClickListener(v -> {
        });
        binding.btnUpdate.setOnClickListener(v -> {
        });
        binding.btnQuery.setOnClickListener(v -> {
            Logger.d("btnQuery...");
//          GreenDaoManager.getInstance().queryAll();
            GreenDaoManager.getInstance().query();
        });
    }

}
