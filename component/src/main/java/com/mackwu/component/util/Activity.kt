package com.mackwu.component.util

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.Fragment
import android.util.Log
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
fun finishActivity(activity: Activity) {
    activityStack.remove(activity)
    activity.finish()
}

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

fun finishAllActivity(cls: Class<*>) {
    for (activity in activityStack) {
        if (activity.javaClass != cls) activity.finish()
    }
    activityStack.clear()
}

/**
 * 得到指定类名的Activity
 */
fun getActivity(cls: Class<*>): Activity? {
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

fun Fragment.startActivityCls(cls: Class<*>) {
    startActivity(Intent(activity, cls))
}

/**
 * Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
 */
fun Service.startActivityCls(cls: Class<*>) {
    startActivity(Intent(this, cls).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}

fun Activity.startActivityClsForResult(cls: Class<*>, requestCode: Int) {
    startActivityForResult(Intent(this, cls), requestCode)
}

fun Activity.startTelActivity(tel: String) {
    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel")).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })
}

fun Activity.startSettingActivity() {
    startActivity(Intent()
            .apply { action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS }
            .apply { data = Uri.fromParts("package", packageName, null) })
}

fun Activity.startGpsActivity() {
    startActivityForResult(Intent().apply { action = Settings.ACTION_LOCATION_SOURCE_SETTINGS }, 0x01)
}

/**
 * 启动另一个进程的Activity
 * 通过包名和类名启动
 */
fun Activity.startPackage(packageName: String, activityName: String) {
    try {
        startActivity(Intent().apply { component = ComponentName(packageName, activityName) })
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 通过action
 */
fun Activity.startAction(action: String) {
    try {
        startActivity(Intent(action))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 通过LaunchIntent
 */
fun Activity.startLaunch(packageName: String) {
    val intent = packageManager.getLaunchIntentForPackage(packageName)
    intent?.run {
        startActivity(this)
    }
}

/**
 * 通过包名获取主Activity。然后启动
 */
fun Context.startPackage(packageName: String){
    try {
        val activityName = queryActivity(packageName)
        startActivity(Intent().apply { component = ComponentName(packageName, activityName) })
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * 通过包名获取Activity名称
 * 注：要根据实际情况配置Intent。有时候Activity没有配置CATEGORY_LAUNCHER，而是配置了LEANBACK_LAUNCHER。或者配置了多个ACTION_MAIN
 * <action android:name="android.intent.action.MAIN" />
 * <category android:name="android.intent.category.LAUNCHER" />
 */
fun Context.queryActivity(packageName: String): String {
    val intent = Intent(Intent.ACTION_MAIN)
//            .apply { addCategory(Intent.CATEGORY_LAUNCHER) }
            .apply { setPackage(packageName) }
    val resolveInfos = applicationContext.packageManager.queryIntentActivities(intent, 0)
    for (resolveInfo in resolveInfos) {
        val activityName = resolveInfo.activityInfo.name
//        Log.d("TAG", "activityInfo: $activityName")
        return activityName ?: ""
    }
    return ""
}

/**
 * 获取所有应用包名
 */
fun Context.queryPackage(): List<String>{
    val intent = Intent(Intent.ACTION_MAIN)
    val resolveInfos = applicationContext.packageManager.queryIntentActivities(intent, 0)
    val packageNames = arrayListOf<String>()
    for (resolveInfo in resolveInfos) {
        val packageName = resolveInfo.activityInfo.packageName
        if (packageName.isNullOrEmpty()) continue
        packageNames.add(packageName)
    }
    return packageNames
}

/**
 * 通过包名获取版本号
 * android.content.pm.PackageManager$NameNotFoundException: xxx
 */
fun Context.queryVersionCode(packageName: String): Int{
    try {
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
        return packageInfo.versionCode
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return -1
}

/**
 * 通过DeepLink
 */
fun Activity.startDeepLink(packageName: String, activityName: String, uriString: String) {
    try {
        val intent = Intent()
                .apply { action = Intent.ACTION_MAIN }
                .apply { addCategory(Intent.CATEGORY_LAUNCHER) }
                .apply { component = ComponentName(packageName, activityName) }
                .apply { data = Uri.parse(uriString) } //"https://www.mackwu.com/test?query=xxx"
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}



