package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.OnLifecycleEvent;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.MainActivityBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/10/11 11:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LifecycleActivity extends BaseActivity<BaseViewModel, MainActivityBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        MyLifecycleObserver observer = new MyLifecycleObserver();
        getLifecycle().addObserver(observer);
        LogUtil.d("XXXXXXXXXXX");
    }

    private static class MyLifecycleObserver implements LifecycleObserver {
        public static final String TAG = MyLifecycleObserver.class.getSimpleName();

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate(@NonNull LifecycleOwner owner) {
            LogUtil.d(TAG, "onCreate...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart() {
            LogUtil.d(TAG, "onStart...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume() {
            LogUtil.d(TAG, "onResume...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause() {
            LogUtil.d(TAG, "onPause...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
            LogUtil.d(TAG, "onStop...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy(@NonNull LifecycleOwner owner) {
            LogUtil.d(TAG, "onDestroy...");
        }
    }

}
