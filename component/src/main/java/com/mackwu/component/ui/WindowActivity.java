package com.mackwu.component.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.WindowActivityBinding;
import com.mackwu.component.ui.window.Window;
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


        View view = LayoutInflater.from(this).inflate(R.layout.window_layout_test, null);
        view.findViewById(R.id.iv_test).setOnClickListener(v -> Toast.makeText(this, "XXXXXXXXXXXXXXXXXXXXX", Toast.LENGTH_SHORT).show());

        Window.with(this)
                .tag("action")
                .view(view)
                .width(200)
                .height(200)
                .build();

        binding.btnShow.setOnClickListener(v -> Window.get("action").show());
        binding.btnHide.setOnClickListener(v -> Window.get("action").hide());
        binding.btnUpdate.setOnClickListener(v -> {

        });
    }

}
