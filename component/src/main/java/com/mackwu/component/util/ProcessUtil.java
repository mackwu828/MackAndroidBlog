package com.mackwu.component.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * ===================================================
 * Created by MackWu on 2021/11/4 15:04
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ProcessUtil {

    /**
     * 获取当前进程pid
     *
     * @return 当前进程pid
     */
    public static int getCurrentProcessPid() {
        return android.os.Process.myPid();
    }

    /**
     * 获取当前进程名称
     *
     * @return 当前进程名称
     */
    @SuppressWarnings("JavaReflectionInvocation")
    @SuppressLint("PrivateApi")
    public static String getCurrentProcessName() {
        String currentProcessName = "";

        // Android9.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            currentProcessName = Application.getProcessName();
        }

        // 反射获取
        if (TextUtils.isEmpty(currentProcessName)) {
            try {
                Class<?> activityThreadClass = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader());
                final Method declaredMethod = activityThreadClass.getDeclaredMethod("currentProcessName", (Class<?>[]) new Class[0]);
                declaredMethod.setAccessible(true);
                final Object invoke = declaredMethod.invoke(null);
                if (invoke instanceof String) {
                    currentProcessName = (String) invoke;
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return currentProcessName;
    }

    /**
     * 获取主进程名称
     *
     * @param context 上下文
     * @return 主进程名称
     */
    public static String getMainProcessName(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getApplicationInfo(context.getPackageName(), 0).processName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 当前进程是否是主进程
     *
     * @param context 上下文
     * @return true: 是  false: 否
     */
    public static boolean isMainProcess(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        int currentProcessPidPid = getCurrentProcessPid();
        String mainProcessName = getMainProcessName(context);
        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            //            LogUtil.d("pid: " + runningAppProcess.pid + ", processName: " + runningAppProcess.processName);
            if (runningAppProcess.pid == currentProcessPidPid && runningAppProcess.processName.equals(mainProcessName)) {
                return true;
            }
        }
        return false;
    }

    public static String getProcessInfo(Context context){
        return "pid: " + getCurrentProcessPid() + ", processName: " + getCurrentProcessName() + ", isMainProcess: " + isMainProcess(context);
    }
}
