package com.mackwu.component.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2020/8/25 15:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class PackageUtil {

    /**
     * 获取所有应用包名
     */
    public static List<String> getPackageNames(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        List<String> packageNames = new ArrayList<>();
        for (ResolveInfo resolveInfo : resolveInfos) {
            if (resolveInfo != null) {
                String packageName = resolveInfo.activityInfo.packageName;
                packageNames.add(packageName);
            }
        }
        return packageNames;
    }

    /**
     * 获取已安装的应用包名
     */
    public static List<String> getInstalledPackageNames(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<>();
        for (PackageInfo installedPackage : installedPackages) {
            if (installedPackage != null) {
                String packageName = installedPackage.packageName;
                packageNames.add(packageName);
            }
        }
        return packageNames;
    }

    /**
     * 应用是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     * @return
     */
    public static boolean isInstalled(Context context, String packageName) {
        return getInstalledPackageNames(context).contains(packageName);
    }

    /**
     * 获取activity名称。
     * 要根据实际情况配置Intent。有时候Activity没有配置CATEGORY_LAUNCHER，而是配置了LEANBACK_LAUNCHER。或者配置了多个ACTION_MAIN。
     *
     * @param context     上下文
     * @param packageName 包名
     * @return
     */
    public static String getActivityName(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(packageName);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        String activityName = "";
        for (ResolveInfo resolveInfo : resolveInfos) {
            activityName = resolveInfo.activityInfo.name;
        }
        return activityName;
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
