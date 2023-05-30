package com.mackwu.component.func.screen;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PowerManager;

import com.mackwu.base.util.Logger;
import com.mackwu.component.util.ShellUtils;

/**
 * ===================================================
 * Created by MackWu on 2022/3/4 16:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 屏幕工具类。
 * 息屏
 * 亮屏
 * 是否亮屏
 * 锁屏
 * 解锁屏幕
 * 设置管理员权限
 * 移除管理员权限
 */
public final class ScreenUtil {

    /**
     * 息屏
     *
     * @param context context
     */
    public static void turnOffScreen(Context context) {
        Logger.d("turnOffScreen...");
//        if (!isScreenOn(context)) {
//            return;
//        }
        lockScreen(context);
    }

    /**
     * 亮屏
     *
     * @param context context
     */
    public static void turnOnScreen(Context context) {
        Logger.d("turnOnScreen...");
//        if (isScreenOn(context)) {
//            return;
//        }
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "power:wakeLock");
        wakeLock.acquire(1000);
        wakeLock.release();
    }

    /**
     * 是否亮屏
     */
    public static boolean isScreenOn(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            isScreenOn = powerManager.isInteractive();
        } else {
            isScreenOn = powerManager.isScreenOn();
        }
        Logger.d("isScreenOn...  " + isScreenOn);
        return isScreenOn;
    }

    /**
     * 锁屏
     *
     * @param context context
     */
    public static void lockScreen(Context context) {
        Logger.d("lockScreen...");
        DevicePolicyManager policyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName = new ComponentName(context, MyDeviceAdminReceiver.class);
        boolean isAdminActive = policyManager.isAdminActive(componentName);
        if (!isAdminActive) {
            setDeviceAdmin(context);
        }
        policyManager.lockNow();
    }

    /**
     * 解锁屏幕
     *
     * @param context context
     */
    public static void unlockScreen(Context context) {
        Logger.d("unlockScreen...");
//        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//        KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("unLock");
//        keyguardLock.reenableKeyguard();
//        keyguardLock.disableKeyguard();
    }

    /**
     * 设置管理员权限
     */
    public static void setDeviceAdmin(Context context) {
        String deviceAdminReceiverName = context.getPackageName() + "/" + MyDeviceAdminReceiver.class.getCanonicalName();
        ShellUtils.execCommand("dpm set-active-admin " + deviceAdminReceiverName, false);
    }

    /**
     * 移除管理员权限
     * adb shell dpm remove-active-admin com.mackwu.component/com.mackwu.component.func.screen.MyDeviceAdminReceiver
     */
    public static void removeDeviceAdmin(Context context) {
        String deviceAdminReceiverName = context.getPackageName() + "/" + MyDeviceAdminReceiver.class.getCanonicalName();
        ShellUtils.execCommand("dpm remove-active-admin " + deviceAdminReceiverName, false);
    }

}
