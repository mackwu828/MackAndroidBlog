package com.mackwu.component.func.web;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2023/1/17 17:19
 */
public interface WebClient {

    void bindWebService();

    void unbindWebService();

    class WebClientImpl implements WebClient {

        private final Context context;
        private IWebService binder;
        private final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                binder = IWebService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                binder = null;
            }
        };

        public WebClientImpl(Context context) {
            this.context = context;
        }

        @Override
        public void bindWebService() {
            Logger.d("bindWebService start...");
            Intent intent = new Intent(context, WebService.class);
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
//            new Thread(() -> {
//                synchronized (this) {
//                    Intent intent = new Intent(context, WebService.class);
//                    context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
//                }
//            }).start();
        }

        @Override
        public void unbindWebService() {

        }
    }
}
