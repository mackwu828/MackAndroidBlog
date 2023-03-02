package com.mackwu.component.func.web;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * @author MackWu
 * @since 2023/1/17 16:56
 */
public class WebService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
