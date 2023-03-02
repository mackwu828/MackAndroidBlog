package com.mackwu.component.service;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import com.mackwu.base.util.Logger;
import com.mackwu.component.func.window.test.WebWindow;
import com.mackwu.component.util.ProcessUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/11/10 10:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WebService extends LifecycleService {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.d("onCreate...  " + ProcessUtil.getProcessInfo(this));
        WebWindow.getInstance();
    }

    @Nullable
    @Override
    public IBinder onBind(@NonNull Intent intent) {
        super.onBind(intent);
//        return new MyBinder();
        return null;
    }

}
