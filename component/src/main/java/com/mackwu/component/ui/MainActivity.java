package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.ActivityStartUtil;
import com.mackwu.component.databinding.MainActivityBinding;
import com.mackwu.component.ui.viewmodel.MainViewModel;
import com.mackwu.component.ui.widget.DialogActivity;
import com.yhao.floatwindow.FloatWindow;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MainActivity extends BaseActivity<MainViewModel, MainActivityBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {
            ActivityStartUtil.startActivity(this, HttpActivity.class);
        });
    }

}
