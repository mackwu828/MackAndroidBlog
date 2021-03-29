package com.mackwu.component.ui;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.mackwu.component.core.MyService;

/**
 * ===================================================
 * Created by MackWu on 2020/8/18 16:08
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ServiceActivity extends BaseTestActivity {

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder binder = (MyService.MyBinder) service;
            MyService myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
    }

}
