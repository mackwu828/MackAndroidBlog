package com.mackwu.component.service;

import android.content.Intent;

import androidx.lifecycle.LifecycleService;

/**
 * ===================================================
 * Created by MackWu on 2020/8/18 16:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyService extends LifecycleService {

    public static final String TAG = MyService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        MyObserver myObserver = new MyObserver();
        getLifecycle().addObserver(myObserver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
