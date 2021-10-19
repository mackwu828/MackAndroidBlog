package com.mackwu.component.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.component.databinding.ActivityTestBinding;
import com.mackwu.component.ui.viewmodel.HandlerViewModel;

import java.lang.ref.WeakReference;

/**
 * ===================================================
 * Created by MackWu on 2021/1/27 16:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HandlerActivity extends BaseActivity<HandlerViewModel, ActivityTestBinding> {

    private static final Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyHandler handler = new MyHandler(this);
        handler.postDelayed(runnable, 5 * 60 * 1000);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    private static class MyHandler extends Handler {

        private final WeakReference<HandlerActivity> weakReference;

        public MyHandler(HandlerActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            HandlerActivity handlerActivity = weakReference.get();
        }
    }

}
