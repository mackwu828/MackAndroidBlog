package com.mackwu.component.service;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import com.mackwu.base.util.LogUtil;
import com.mackwu.component.IWebInterface;
import com.mackwu.component.ui.window.WebWindow;
import com.mackwu.component.util.ProcessUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/11/10 10:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WebService extends LifecycleService {

    public static final String TAG = WebService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG, "onCreate...  " + ProcessUtil.getProcessInfo(this));
        WebWindow.getInstance();
    }

    @Nullable
    @Override
    public IBinder onBind(@NonNull Intent intent) {
        super.onBind(intent);
        return new MyBinder();
    }

    private static class MyBinder extends IWebInterface.Stub {

        @Override
        public void show() throws RemoteException {
            WebWindow.getInstance().show();
        }

        @Override
        public void hide() throws RemoteException {
            WebWindow.getInstance().hide();
        }

        @Override
        public void loadUrl() throws RemoteException {
            WebWindow.getInstance().loadUrl();
        }
    }
}
