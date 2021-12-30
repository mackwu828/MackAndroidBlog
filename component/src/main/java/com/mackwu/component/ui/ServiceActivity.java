package com.mackwu.component.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityServiceBinding;
import com.mackwu.component.service.MyService;

/**
 * ===================================================
 * Created by MackWu on 2020/8/18 16:08
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ServiceActivity extends BaseActivity<BaseViewModel, ActivityServiceBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        startService(new Intent(this, MyService.class));

        binding.btnStartService.setOnClickListener(v -> {
        });
        binding.btnStopService.setOnClickListener(v -> {
        });
    }

}
