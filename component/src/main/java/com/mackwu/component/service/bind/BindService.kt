package com.mackwu.service.start

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.mackwu.component.service.bind.MyBinder
import com.mackwu.component.service.lifecycle.LifecycleService

/**
 * ================================================
 * Created by MackWu on 2019/8/23 10:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 绑定服务
 *
 * <h3>绑定服务的生命周期<h3>
 * onCreate -> onBind -> onUnbind -> onDestroy
 *
 * <h3>扩展Binder类<h3/>
 * 通过继承Binder类，只能与当前进程的其他组件进行交互，如果想要与其他进程进行交互，需要用Messenger或AIDL
 *
 * 绑定服务分为服务端和客户端。
 * 服务端：指的就是Service。在Service中定义一个Binder对象，返回当前服务的实例，其他组件可以通过该实例调用服务的方法
 * 客户端：指Activity等其他组件，用来绑定服务，绑定服务时需要传入ServiceConnection对象。当客户端与服务端连接成功时，
 * 会回调到ServiceConnection的onServiceConnected方法中
 * @see StartActivity
 *
 *
 * <h3>测试<h3/>
 * @see StartActivity
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
class BindService : Service() {

    private val binder = MyBinder(this)

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate...")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("TAG", "onBind...")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("TAG", "onUnbind...")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
    }

    fun test() {
        Toast.makeText(applicationContext, "has bound Service", Toast.LENGTH_SHORT).show()
    }

}