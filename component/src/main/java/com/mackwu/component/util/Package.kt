package com.mackwu.component.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

/**
 * ===================================================
 * Created by MackWu on 2020/3/11 11:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 获取设备中所有应用包名
 */
fun Context.queryAllPackages(): List<String>{
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
 * 获取设备中所有已安装的应用包名
 */
fun Context.queryAllInstalledPackages(): List<String>{
    val packageInfos = packageManager.getInstalledPackages(0)
    val packageNames = arrayListOf<String>()
    for (packageInfo in packageInfos) {
        val packageName = packageInfo.packageName
        if (packageName.isNullOrEmpty()) continue
        packageNames.add(packageName)
    }
    return packageNames
}

/**
 * 包名是否存在
 */
fun Context.isPackageInstalled(packageName: String): Boolean{
    return queryAllInstalledPackages().contains(packageName)
}


/**
 * 通过包名获取Activity名称
 * 注：要根据实际情况配置Intent。有时候Activity没有配置CATEGORY_LAUNCHER，而是配置了LEANBACK_LAUNCHER。或者配置了多个ACTION_MAIN
 * <action android:name="android.intent.action.MAIN" />
 * <category android:name="android.intent.category.LAUNCHER" />
 */
fun Context.queryActivityByPackage(packageName: String): String {
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
 * 通过包名获取版本号
 * android.content.pm.PackageManager$NameNotFoundException: xxx
 */
fun Context.queryVersionCodeByPackage(packageName: String): Int{
    try {
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA)
        return packageInfo.versionCode
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return -1
}