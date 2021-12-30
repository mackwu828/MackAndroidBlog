package com.mackwu.component;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.webkit.WebView;

import com.mackwu.base.util.LogUtil;
import com.mackwu.component.core.DbManager;
import com.mackwu.component.core.http.AliDns;
import com.mackwu.component.service.WebService;
import com.mackwu.component.util.ProcessUtil;

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
