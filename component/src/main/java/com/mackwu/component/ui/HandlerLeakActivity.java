package com.mackwu.component.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityTestBinding;
import com.mackwu.component.ui.HandlerActivity;

import java.lang.ref.WeakReference;

/**
 * ===================================================
 * Created by MackWu on 2022/5/16 10:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HandlerLeakActivity extends BaseActivity<BaseViewModel, ActivityTestBinding> {

    private static final Runnable runnable = () -> {

    };

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        MyHandler handler = new MyHandler(this);
        handler.postDelayed(runnable, 5 * 60 * 1000);
    }

    private static class MyHandler extends Handler {

        private final WeakReference<Activity> weakReference;

        public MyHandler(Activity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Activity activity = weakReference.get();
        }
    }
}
