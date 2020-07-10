package com.mackwu.component.jetpack;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.mackwu.component.R;
import com.mackwu.component.base.BaseActivity;

/**
 * ===================================================
 * Created by MackWu on 2020/6/30 11:48
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ViewModelActivity extends BaseActivity {

    private UserViewModel userViewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(@Nullable final Bundle savedInstanceState) {
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
    }

    @Override
    public void initData(@Nullable final Bundle savedInstanceState) {

    }

}
