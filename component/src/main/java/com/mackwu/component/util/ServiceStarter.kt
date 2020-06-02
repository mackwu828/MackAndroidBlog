package com.mackwu.component.util

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.content.ServiceConnection

/**
 * ===================================================
 * Created by MackWu on 2020/2/17 16:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * start Service
 */
fun Activity.startServiceCls(cls: Class<out Service>) = startService(Intent(this, cls))

/**
 * stop Service
 */
fun Activity.stopServiceCls(cls: Class<out Service>) = stopService(Intent(this, cls))

/**
 * 通过action启动另一个进程的Service
 * 异常：java.lang.IllegalArgumentException: Service Intent must be explicit: Intent { act=com.mackwu.component.ACTION_SERVICE }
 * 从Android5.0开始，Intent必须是显示声明。如果要隐式声明的话，必须要加包名
 */
fun Activity.startActionService(packageName: String, action: String) = try {
    startService(Intent(action).apply { setPackage(packageName) })
} catch (e: Exception) {
    e.printStackTrace()
}

/**
 * bind Service
 */
fun Activity.bindService(cls: Class<out Service>, serviceConnection: ServiceConnection) {
    bindService(Intent(this, cls), serviceConnection, Service.BIND_AUTO_CREATE)
}

/**
 * unbind Service
 */
fun Activity.unbindService(serviceConnection: ServiceConnection){
    unbindService(serviceConnection)
}