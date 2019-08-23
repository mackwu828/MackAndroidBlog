package com.mackwu.service.start

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * ================================================
 * Created by MackWu on 2019/8/23 10:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 启动服务
 * 启动服务后，即使Activity被销毁，服务仍在后台运行
 *
 * <h3>启动服务的生命周期<h3/>
 * onCreate -> onStartCommand -> onDestroy
 *
 * <h3>测试<h3/>
 * @see StartActivity
 * 点击1次startService，执行了1次onCreate和onStartCommand
 * //    08-23 15:22:53.316 2604-2604/com.mackwu.service D/TAG: onCreate...
 * //    08-23 15:22:53.317 2604-2604/com.mackwu.service D/TAG: onStartCommand...
 *
 * 再继续点击2次startService，执行了2次onStartCommand
 * //    08-23 15:22:56.956 2604-2604/com.mackwu.service D/TAG: onStartCommand...
 * //    08-23 15:22:57.652 2604-2604/com.mackwu.service D/TAG: onStartCommand...
 *
 * 退出Activity，onDestroy并没有执行
 */
class StartService: Service(){

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("TAG", "onBind...")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 在服务第一次创建时调用
     */
    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate...")
    }

    /**
     * 在服务启动时调用。每次启动都会调用
     * @return START_STICKY 服务因内存不足被系统销毁后，会重新创建，然后调用onStartCommand，参数intent为空
     * START_NOT_STICKY 不会重新创建
     * START_REDELIVER_INTENT 会重新创建，并保存了之前的intent
     * START_STICKY_COMPATIBILITY START_STICKY的兼容版本
     *
     * super是返回START_STICKY或START_STICKY_COMPATIBILITY
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "onStartCommand...")
        return Service.START_STICKY
//        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * 在服务被销毁时调用
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
    }


}