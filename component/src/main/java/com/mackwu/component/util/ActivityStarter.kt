package com.mackwu.component.util

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 19:35
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * startActivity
 */
fun Activity.startActivityCls(cls: Class<*>) = startActivity(Intent(this, cls))

fun Fragment.startActivityCls(cls: Class<*>) = activity?.startActivityCls(cls)

/**
 * 在Service中启动Activity需要添加FLAG_ACTIVITY_NEW_TASK。
 * Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag. Is this really what you want?
 */
fun Service.startActivityCls(cls: Class<*>) = startActivity(Intent(this, cls).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

fun Context.startActivityCls(cls: Class<*>) = startActivity(Intent(this, cls).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

/**
 * startActivityForResult
 */
fun Activity.startActivityClsForResult(cls: Class<*>, requestCode: Int) = startActivityForResult(Intent(this, cls), requestCode)

fun Fragment.startActivityClsForResult(cls: Class<*>, requestCode: Int) = activity?.startActivityClsForResult(cls, requestCode)


/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * 跨进程启动 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
/**
 * 通过包名和Activity名称启动另一个进程的Activity
 * 异常1：java.lang.SecurityException: Permission Denial: starting Intent ... not exported from uid 10066
 * 异常2：android.content.ActivityNotFoundException: Unable to find explicit activity class ... have you declared this activity in your AndroidManifest.xml?
 */
fun Activity.startPackageActivity(packageName: String, activityName: String) = try {
    startActivity(Intent().apply { component = ComponentName(packageName, activityName) })
} catch (e: Exception) {
    e.printStackTrace()
}

fun Fragment.startPackageActivity(packageName: String, activityName: String) = activity?.startPackageActivity(packageName, activityName)

/**
 * 通过action启动另一个进程的Activity
 * 异常：android.content.ActivityNotFoundException: No Activity found to handle Intent { act=com.xxx }
 */
fun Activity.startActionActivity(action: String) = try {
    startActivity(Intent(action))
} catch (e: Exception) {
    e.printStackTrace()
}

fun Fragment.startActionActivity(action: String) = activity?.startActionActivity(action)

/**
 * 通过LaunchIntent启动另一个进程的MainActivity(android.intent.action.MAIN)
 */
fun Activity.startLaunchActivity(packageName: String) = packageManager.getLaunchIntentForPackage(packageName)?.run { startActivity(this) }

fun Fragment.startLaunchActivity(packageName: String) = activity?.startLaunchActivity(packageName)


/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * 启动特殊页面 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
/**
 * 启动拨号页面
 */
fun Activity.startTelActivity(tel: String) = startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tel")).apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

/**
 * 启动设置页面
 */
fun Activity.startSettingActivity() = startActivity(Intent().apply {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    data = Uri.fromParts("package", packageName, null)
})

/**
 * 启动GPS页面
 */
fun Activity.startGpsActivity() = startActivityForResult(Intent().apply { action = Settings.ACTION_LOCATION_SOURCE_SETTINGS }, 0x01)

/**
 * 启动指定的uri
 */
fun Activity.startUriActivity(uriString: String) = startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(uriString) })

fun Activity.startUriActivity(packageName: String, activityName: String, uriString: String) = startActivity(Intent(Intent.ACTION_VIEW).apply {
    data = Uri.parse(uriString)
    setClassName(packageName, activityName)
})

/**
 * 跳转到网页
 */
fun Activity.startUriWeb(uriString: String) = startActivity(Intent().apply {
    action = "android.intent.action.VIEW"
    data = Uri.parse(uriString)
})

/**
 * 启动卸载页面
 */
fun Context.startUninstallActivity(packageName: String) = startActivity(Intent(Intent.ACTION_DELETE).apply { data = Uri.parse("package:$packageName") })