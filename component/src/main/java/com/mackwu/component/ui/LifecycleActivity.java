package com.mackwu.component.ui;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.Logger;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.databinding.ActivityMainBinding;

/**
 * ===================================================
 * Created by MackWu on 2021/10/11 11:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class LifecycleActivity extends BaseActivity<BaseViewModel, ActivityMainBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        MyLifecycleObserver observer = new MyLifecycleObserver();
        getLifecycle().addObserver(observer);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.d("onSaveInstanceState...");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.d("onRestoreInstanceState...");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.d("onConfigurationChanged...");
        int orientation = newConfig.orientation;
        // 竖屏
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
        // 横屏
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }
    }

    private static class MyLifecycleObserver implements LifecycleObserver {

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate(@NonNull LifecycleOwner owner) {
            Logger.d("onCreate...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart() {
            Logger.d("onStart...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume() {
            Logger.d("onResume...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause() {
            Logger.d("onPause...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop() {
            Logger.d("onStop...");
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy(@NonNull LifecycleOwner owner) {
            Logger.d("onDestroy...");
        }
    }

}
