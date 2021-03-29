package com.mackwu.component.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
     * 获取所有已安装的应用包名。
     */
    public static List<String> getAllInstalledPackageNames(Context context) {
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
     * 获取应用图标
     *
     * @param context     上下文
     * @param packageName 包名
     * @return objects[0]应用图标、objects[1]是否是banner
     */
    public static Object[] getAppIcon(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        Object[] objects = new Object[2];
        Drawable drawable = null;
        boolean isBanner = false;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = packageManager.getPackageInfo(packageName, 0).applicationInfo.loadBanner(packageManager);
                if (drawable != null) {
                    isBanner = true;
                } else {
                    ComponentName componentName = new ComponentName(packageName, getActivityName(context, packageName));
                    drawable = packageManager.getActivityBanner(componentName);
                    if (drawable != null) {
                        isBanner = true;
                    } else {
                        drawable = packageManager.getApplicationIcon(packageName);
                    }
                }
            } else {
                drawable = packageManager.getApplicationIcon(packageName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        objects[0] = drawable;
        objects[1] = isBanner;
        return objects;
    }

    /**
     * 根据包名获取activity名称。
     * 注：intent只需添加ACTION_MAIN。因为有的应用的MainActivity没有配置CATEGORY_LAUNCHER，而是配置了LEANBACK_LAUNCHER。或者配置了多个ACTION_MAIN。
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static String getActivityName(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setPackage(packageName);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        String activityName = "";
        for (ResolveInfo resolveInfo : resolveInfos) {
            if (resolveInfo != null) {
                activityName = resolveInfo.activityInfo.name;
            }
        }
        return activityName;
    }


    /*
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * 其他 * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    /**
     * 应用是否安装
     *
     * @param context     上下文
     * @param packageName 包名
     */
    @Deprecated
    public static boolean isAppInstalled2(Context context, String packageName) {
        return getAllInstalledPackageNames(context).contains(packageName);
    }

    /**
     * 应用是否安装。
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_META_DATA);
            return true;
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * 启动第三方应用
     */
    public static void startThirdPartyActivity(Context context, String packageName){
        try{
            PackageManager packageManager = context.getPackageManager();
            Intent it = packageManager.getLaunchIntentForPackage(packageName);
            if (it != null) {
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(it);
            } else {
                String activityName = getActivityName(context, packageName);
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName componentName = new ComponentName(packageName, activityName);
                intent.setComponent(componentName);
                if (intent.resolveActivityInfo(context.getPackageManager(), PackageManager.MATCH_DEFAULT_ONLY) != null) {
                    context.startActivity(intent);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取栈顶元素的包名
     */
    public static String getTopPackageName(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = "";
        if (activityManager != null) {
            List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(1);
            if (runningTasks != null && !runningTasks.isEmpty()) {
                ComponentName componentName = runningTasks.get(0).topActivity;
                packageName = componentName.getPackageName();
            }
        }
        return packageName;
    }

    /**
     * 关闭第三方应用
     * <p>
     * 注：需要系统权限。activityManager.killBackgroundProcesses not working
     * https://stackoverflow.com/questions/9886525/killbackgroundprocesses-is-not-working
     */
    public static void killThirdPartyActivity(Context context, String packageName) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            Method forceStopPackage = activityManager.getClass().getDeclaredMethod("forceStopPackage", String.class);
            forceStopPackage.setAccessible(true);
            forceStopPackage.invoke(activityManager, packageName);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
