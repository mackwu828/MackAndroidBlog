package com.mackwu.component;

import android.app.Application;

import com.mackwu.component.core.db.DbManager;

/**
 * ===================================================
 * Created by MackWu on 2020/12/7 16:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ComponentApp extends Application {

    public static final String TAG = ComponentApp.class.getSimpleName();
    private static ComponentApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        DbManager.getInstance().init(this);
    }

    public static ComponentApp getInstance() {
        return instance;
    }

}
