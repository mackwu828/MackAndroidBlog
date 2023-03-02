package com.mackwu.component.service;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.mackwu.base.util.Logger;
import com.mackwu.component.ui.livedata.TestLiveData;

/**
 * ===================================================
 * Created by MackWu on 2021/4/6 17:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        new TestLiveData().observeForever(s -> {
            Logger.d("TestLiveData...  s: " + s);
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
    }
}
