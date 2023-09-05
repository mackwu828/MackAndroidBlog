package com.mackwu.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2023/6/19 11:12
 */
public class MessengerService extends Service {

    private Messenger messenger;
    private final Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Logger.d("handleMessage...  " + msg.obj);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        messenger = new Messenger(handler);
        return messenger.getBinder();
    }

    public Messenger getMessenger() {
        return messenger;
    }
}
