package com.mackwu.component.util;

import android.content.Context;
import android.provider.Settings;
import android.service.dreams.DreamService;

import java.util.Objects;

/**
 * ===================================================
 * Created by MackWu on 2020/10/9 18:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ScreensaverUtil {

    /**
     * 开启屏保
     * settings get secure screensaver_enabled 屏保是否打开
     * settings put secure screensaver_enabled 1 开启屏保
     * settings put secure screensaver_enabled 0 关闭屏保
     */
    public static void setScreensaverEnabled(Context context) {
        Settings.Secure.putInt(context.getContentResolver(), "screensaver_enabled", 1);
    }

    /**
     * 关闭屏保
     */
    public static void setScreensaverDisabled(Context context) {
        Settings.Secure.putInt(context.getContentResolver(), "screensaver_enabled", 0);
    }

    /**
     * 屏保是否打开
     */
    public static boolean isScreensaverEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "screensaver_enabled", 0) == 1;
    }

    /**
     * 屏保启动场景，睡眠时开启屏保
     * settings get secure screensaver_activate_on_sleep
     * settings put secure screensaver_activate_on_sleep 1 睡眠时开启屏保
     * settings put secure screensaver_activate_on_sleep 0 睡眠时关闭屏保
     */
    public static void setScreensaverActivateOnSleep(Context context) {
        Settings.Secure.putInt(context.getContentResolver(), "screensaver_activate_on_sleep", 1);
    }

    /**
     * 屏保启动场景，睡眠时关闭屏保
     */
    public static void setScreensaverInActivateOnSleep(Context context) {
        Settings.Secure.putInt(context.getContentResolver(), "screensaver_activate_on_sleep", 0);
    }

    public static boolean isScreensaverActivateOnSleep(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "screensaver_activate_on_sleep", 0) == 1;
    }

    /**
     * 屏保启动场景，连接底座时开启启动屏保
     * settings get secure screensaver_activate_on_dock
     * settings put secure screensaver_activate_on_dock 1 连接底座时开启屏保
     * settings put secure screensaver_activate_on_dock 0 连接底座时关闭屏保
     */
    public static void setScreensaverActivateOnDock(Context context) {
        Settings.Secure.putInt(context.getContentResolver(), "screensaver_activate_on_dock", 1);
    }

    /**
     * 屏保启动场景，连接底座时关闭启动屏保
     */
    public static void setScreensaverInActivateOnDock(Context context) {
        Settings.Secure.putInt(context.getContentResolver(), "screensaver_activate_on_dock", 0);
    }

    public static boolean isScreensaverActivateOnDock(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "screensaver_activate_on_dock", 0) == 1;
    }

    /**
     * 设置屏保时间
     * settings get system screen_off_timeout 默认值2147483647
     * settings put system screen_off_timeout 15000
     * settings put system screen_off_timeout 30000
     * settings put system screen_off_timeout 60000
     *
     * @param context          上下文
     * @param screenOffTimeout 屏保时间
     */
    public static void setScreenOffTimeout(Context context, int screenOffTimeout) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, screenOffTimeout);
    }

    /**
     * 获取屏保时间
     */
    public static void getScreenOffTimeout(Context context) {
        Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, -1);
    }

    /**
     * 设置屏保服务。默认值com.google.android.deskclock/com.android.deskclock.Screensaver
     * Settings.Secure.SCREENSAVER_COMPONENTS
     * settings get secure screensaver_components
     * settings put secure screensaver_components com.mackwu.component/com.mackwu.component.service.ScreenService
     */
    public static void setScreensaverComponent(Context context, String screensaverComponent) {
        Settings.Secure.putString(context.getContentResolver(), "screensaver_components", screensaverComponent);
    }

    public static String getScreensaverComponent(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), "screensaver_components");
    }

    /**
     * 设置屏保服务
     * @param context context
     * @param serviceCls 屏保服务
     */
    public static void setScreensaverComponent(Context context, Class<? extends DreamService> serviceCls) {
        if (!isScreensaverEnabled(context)) {
            setScreensaverEnabled(context);
        }
        if (!isScreensaverActivateOnDock(context)) {
            setScreensaverActivateOnDock(context);
        }
        if (!isScreensaverActivateOnSleep(context)) {
            setScreensaverActivateOnSleep(context);
        }
        String serviceName = Objects.requireNonNull(serviceCls.getPackage()).getName() + "." + serviceCls.getSimpleName();
        String screensaverComponent = context.getPackageName() + "/" + serviceName;
        if (!screensaverComponent.equals(getScreensaverComponent(context))) {
            setScreensaverComponent(context, screensaverComponent);
        }
    }

}
