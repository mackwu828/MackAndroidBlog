package com.mackwu.component.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.R;
import com.mackwu.component.databinding.ActivityTestBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/3/22 19:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TestActivity<VM extends BaseViewModel, B extends ViewBinding> extends BaseActivity<VM, B> {

    protected Button btnTest;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        btnTest = findViewById(R.id.btn_test);
        btnTest.setText("XXXAAA");
    }
}
