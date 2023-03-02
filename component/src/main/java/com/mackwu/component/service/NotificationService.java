package com.mackwu.component.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

/**
 * ===================================================
 * Created by MackWu on 2020/9/28 14:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 *
 * 通知栏
 */
public class NotificationService extends Service {

    private NotificationManager notificationManager;
    private Notification notification;

    @Override
    public void onCreate() {
        super.onCreate();

        // notificationManager
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // notification
        // android8.0(包括8.0) notification需要渠道
        String channelId = "channel_id";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, "channel_name", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        notification = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("")
                .setContentText("")
                .build();
        startForeground(1, notification);

        // notificationBar
//        notificationBar = new NotificationBar(this);
//        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        WindowManager.LayoutParams windowLayoutParams = new WindowManager.LayoutParams();
//        windowLayoutParams.gravity = Gravity.START | Gravity.TOP;
//        windowLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
//        windowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        windowLayoutParams.format = PixelFormat.TRANSLUCENT;
//        notificationBar.measure(0, 0);
//        windowLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        windowLayoutParams.height = notificationBar.getMeasuredHeight();
//        windowManager.addView(notificationBar, windowLayoutParams);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
