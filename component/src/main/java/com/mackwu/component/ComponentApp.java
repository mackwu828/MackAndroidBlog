package com.mackwu.component;

import android.app.Application;

import com.tencent.mmkv.MMKV;

/**
 * ===================================================
 * Created by MackWu on 2020/12/7 16:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ComponentApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
    }
}
