package com.mackwu.component.util;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

/**
 * ===================================================
 * Created by MackWu on 2020/10/9 18:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ScreensaverUtil {

    /**
     * 设置屏保时间
     * settings put system screen_off_timeout 1000
     * settings get system screen_off_timeout
     *
     * @param context          上下文
     * @param screenOffTimeout 屏保时间
     */
    public static void setScreenOffTimeout(Context context, int screenOffTimeout) {
        ContentResolver contentResolver = context.getContentResolver();
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, screenOffTimeout);
    }

    /**
     * 获取屏保时间
     *
     * @param context 上下文
     */
    public static void getScreenOffTimeout(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Settings.System.getInt(contentResolver, Settings.System.SCREEN_OFF_TIMEOUT, -1);
    }


    /**
     * 开启屏保
     * settings get secure screensaver_enabled
     * settings put secure screensaver_enabled 1 开启屏保
     * settings put secure screensaver_enabled 0 关闭屏保
     */
    public void setScreensaverEnabled(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        Settings.System.putInt(contentResolver, "screensaver_enabled", 1);
    }

    /**
     * 关闭屏保
     */
    public void setScreensaverDisEnabled(Context context){
        ContentResolver contentResolver = context.getContentResolver();
        Settings.System.putInt(contentResolver, "screensaver_enabled", 0);
    }

}
