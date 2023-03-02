package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityAnimBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/11/18 14:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class AnimActivity extends BaseActivity<BaseViewModel, ActivityAnimBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {


        binding.btnStart.setOnClickListener(v -> {
        });
    }




}
