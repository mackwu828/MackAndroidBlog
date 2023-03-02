package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityWindowBinding;
import com.mackwu.component.func.window.test.RedWindow;
import com.mackwu.component.func.window.test.YellowWindow;

/**
 * ===================================================
 * Created by MackWu on 2021/1/4 10:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WindowActivity extends BaseActivity<BaseViewModel, ActivityWindowBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
//        WindowPermission.requestSystemAlertWindow(this, () -> {
//        });

        binding.btnShow.setOnClickListener(v -> {
            RedWindow.getInstance().show();
        });
        binding.btnHide.setOnClickListener(v -> {
            RedWindow.getInstance().hide();
        });
        binding.btnShow2.setOnClickListener(v -> {
            YellowWindow.getInstance().show();
        });
        binding.btnHide2.setOnClickListener(v -> {
            YellowWindow.getInstance().hide();
        });

//        FloatWindow
    }

}
