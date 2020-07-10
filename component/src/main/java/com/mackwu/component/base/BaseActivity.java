package com.mackwu.component.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseActivity extends AppCompatActivity implements IActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "What activity is created? " + this.getClass().getSimpleName());
        setContentView(getLayoutId());
        initView(savedInstanceState);
        initData(savedInstanceState);
    }
}
