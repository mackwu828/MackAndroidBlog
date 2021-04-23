package com.mackwu.component;

import android.app.Application;
import android.content.Context;

import com.mackwu.base.util.LogUtil;
import com.tencent.mmkv.MMKV;

/**
 * ===================================================
 * Created by MackWu on 2020/12/7 16:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ComponentApp extends Application {

    public ComponentApp() {
        LogUtil.d("ComponentApp constructor");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d("ComponentApp onCreate");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LogUtil.d("ComponentApp attachBaseContext");
    }
}
