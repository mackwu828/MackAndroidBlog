package com.mackwu.service.start

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast

/**
 * ================================================
 * Created by MackWu on 2019/8/23 10:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 绑定服务
 *
 * 绑定服务的生命周期
 * onCreate -> onBind -> onUnbind -> onDestroy
 *
 * <h3>测试<h3/>
 * 点击1次bindService，执行了1次onCreate和onBind
 * 08-23 16:37:50.982 2539-2539/com.mackwu.service D/TAG: onCreate...
 * 08-23 16:37:50.982 2539-2539/com.mackwu.service D/TAG: onBind...
 *
 * 再继续点击2次bindService，什么都没执行
 *
 * 退出Activity，执行了onUnbind和onDestroy
 * 08-23 16:38:34.486 2539-2539/com.mackwu.service D/TAG: onUnbind...
 * 08-23 16:38:34.487 2539-2539/com.mackwu.service D/TAG: onDestroy...
 */
class BindService: Service(){

    private val binder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate...")
    }

    /**
     * 绑定服务时调用
     * @return 返回扩展的binder对象
     */
    override fun onBind(intent: Intent?): IBinder? {
        Log.d("TAG", "onBind...")
        return binder
    }

    /**
     * 解绑服务时调用
     */
    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("TAG", "onUnbind...")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
    }

    fun test(){
        Toast.makeText(applicationContext, "has bound Service", Toast.LENGTH_SHORT).show()
    }

    /**
     * 扩展Binder类。只能与当前进程的组件进行交互
     */
    inner class LocalBinder: Binder(){
        // 返回当前服务的实例，其他组件可以通过该实例调用服务的方法
        val service = this@BindService
    }
}