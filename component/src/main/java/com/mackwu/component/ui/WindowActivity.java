package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.WindowActivityBinding;
import com.mackwu.component.ui.window.TestWindow;
import com.mackwu.component.util.WindowUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/1/4 10:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WindowActivity extends BaseActivity<BaseViewModel, WindowActivityBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        WindowUtil.requestSystemAlertWindow(this, () -> {
        });
        binding.btnShow.setOnClickListener(v -> TestWindow.getInstance(this).show());
        binding.btnHide.setOnClickListener(v -> TestWindow.getInstance(this).hide());
        binding.btnUpdate.setOnClickListener(v -> TestWindow.getInstance(this).showRandom());
    }

}
