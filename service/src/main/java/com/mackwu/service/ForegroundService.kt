package com.mackwu.service

import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log

/**
 * ================================================
 * Created by MackWu on 2019/8/23 16:46
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 前台服务。
 * 前台服务会在通知栏显示一个正在运行的图标，前台服务需要通过通知启动。
 * 后台服务的优先级是比较低的，当系统内存不足时容易被系统回收，而使用前台服务，就不会因为系统内存不足而被回收。
 */
class ForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate...")
        // 通知
        val notification = NotificationCompat.Builder(this)
                .setContentTitle("标题")
                .setContentText("内容")
                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()
        // 将服务设置为前台服务
        startForeground(10, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "onStartCommand...")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
        // 移除前台服务
        stopForeground(true)
    }

}