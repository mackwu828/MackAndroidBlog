package com.mackwu.component.util;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

import java.util.Locale;

/**
 * ===================================================
 * Created by MackWu on 2020/9/25 17:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class BrightnessUtil {

    /**
     * 获取系统屏幕亮度值。屏幕亮度值范围（0-255）
     * 命令行：settings get system screen_brightness
     */
    public static int getScreenBrightness(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 125);
    }

    /**
     * 设置系统屏幕亮度值。
     * 例子：ScreenBrightnessUtil.setScreenBrightness(this, 10);
     * 命令行：settings put system screen_brightness 1
     *
     * @param screenBrightness 统屏幕亮度值。屏幕亮度值范围（0-255）
     */
    public static void setScreenBrightness(Context context, int screenBrightness) {
        setManualMode(context);
        ContentResolver contentResolver = context.getContentResolver();
        Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, screenBrightness);
    }

    /**
     * 设置自动调节屏幕亮度。系统会根据光感自动调节。自动调节屏幕亮度模式值为1。
     * 命令行：
     * settings put system screen_brightness_mode 1
     * settings get system screen_brightness_mode
     */
    public static void setAutomaticMode(Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            int mode = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode != Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置手动调节屏幕亮度。手动调节屏幕亮度模式值为0。
     * 命令行：
     * settings put system screen_brightness_mode 0
     * settings get system screen_brightness_mode
     */
    public static void setManualMode(Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            int mode = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
            if (mode != Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置自动/手动调节屏幕亮度
     */
    public static void toggleAutomaticMode(Context context, boolean isAutomatic) {
        if (isAutomatic) {
            setAutomaticMode(context);
        } else {
            setManualMode(context);
        }
    }

    /**
     * 亮度是否是自动调节。1是自动调节，0是手动调节。
     */
    public static boolean isAutomaticMode(Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            int mode = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
            return mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        int a = 12311;
        String aStr = String.valueOf(a);
        float t = 1000f;
        if (a > t) {
            aStr = String.format(Locale.getDefault(), "%.2f", a / t);
        }
        if (a > t * t) {
            aStr = String.format(Locale.getDefault(), "%.2f", a / t / t);
        }
        System.out.println(aStr);
    }

}
