package com.mackwu.component.util

import android.app.Activity
import android.content.Intent

/**
 * ===================================================
 * Created by MackWu on 2020/2/17 16:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 启动服务
 */

/**
 * startService
 */
fun Activity.startServiceCls(cls: Class<*>) = startService(Intent(this, cls))

/**
 * stopService
 */
fun Activity.stopServiceCls(cls: Class<*>) = stopService(Intent(this, cls))


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