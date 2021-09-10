package com.mackwu.component.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.ActivityStackUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityTestBinding;

/**
 * ===================================================
 * Created by MackWu on 2020/11/4 15:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ThirdActivity extends BaseActivity<BaseViewModel, ActivityTestBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> ActivityStackUtil.finishAllActivityExcept(MainActivity.class));
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ThirdActivity.class);
        context.startActivity(intent);
    }
}
