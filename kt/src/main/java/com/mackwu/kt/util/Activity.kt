package com.mackwu.kt.util

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.Fragment
import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2019-05-16 11:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
private val activityStack by lazy { Stack<Activity>() }

/**
 * 添加activity
 */
fun addActivity(activity: Activity) {
    activityStack.add(activity)
}

/**
 * 结束activity
 */
fun finishActivity(activity: Activity?) {
    if (activity != null) {
        activityStack.remove(activity)
        activity.finish()
    }
}

/**
 * 结束activity
 */
fun finishActivity(cls: Class<*>) {
    val iterator = activityStack.iterator()
    while (iterator.hasNext()) {
        val activity = iterator.next() as Activity
        if (activity.javaClass == cls) {
            iterator.remove()
            activity.finish()
        }
    }
}

/**
 * 结束所有activity
 */
fun finishAllActivity() {
    for (activity in activityStack) {
        activity.finish()
    }
    activityStack.clear()
}

fun finishAllActivityWithoutCls(cls: Class<*>) {
    for (activity in activityStack) {
        if (activity.javaClass != cls) activity.finish()
    }
    activityStack.clear()
}

/**
 * 得到指定类名的Activity
 */
fun getActivity(cls: Class<*>): Activity?{
    for (activity in activityStack) {
        if (activity.javaClass == cls) {
            return activity
        }
    }
    return null
}

/**
 * 跳转
 */
fun Activity.startActivityCls(cls: Class<*>) {
    startActivity(Intent(this, cls))
}

fun Activity.startActivityClsForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(Intent(this, cls), requestCode)
}

fun Activity.startTelActivity(tel: String) {
    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel")).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK })
}

fun Activity.startSettingActivity() {
    startActivity(Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.fromParts("package", packageName, null)
    })
}

fun Activity.startGpsActivity() {
    startActivityForResult(Intent().apply { action = Settings.ACTION_LOCATION_SOURCE_SETTINGS }, 0x01)
}

fun Fragment.startActivityCls(cls: Class<*>) {
    startActivity(Intent(activity, cls))
}

fun Service.startActivityCls(cls: Class<*>) {
    startActivity(Intent(this, cls))
}




