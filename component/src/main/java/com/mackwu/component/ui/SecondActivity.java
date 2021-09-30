package com.mackwu.component.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.ActivityStartUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivitySecondBinding;
import com.mackwu.component.databinding.ActivityTestBinding;
import com.mackwu.component.ui.livedata.StrLiveData;

/**
 * ===================================================
 * Created by MackWu on 2020/11/4 15:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class SecondActivity extends BaseActivity<BaseViewModel, ActivitySecondBinding> {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
        }
    };

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {
            StrLiveData.getInstance().postValue("222");
        });

        new Thread(() -> {
            handler.sendEmptyMessage(1);
        }).start();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }
}
