package com.mackwu.component.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityTestBinding;

/**
 * ===================================================
 * Created by MackWu on 2020/11/4 15:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SecondActivity extends TestActivity<BaseViewModel, ActivityTestBinding> {

    public static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
//        ActivityStartUtil.startActivity(this, ThirdActivity.class);
//        binding.btnTest.setOnClickListener(v -> {
//            ActivityStartUtil.startActivity(this, MainActivity.class);
//            finish();
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy...");
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
}
