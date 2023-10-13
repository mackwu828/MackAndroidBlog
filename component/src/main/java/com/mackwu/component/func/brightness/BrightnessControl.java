package com.mackwu.component.func.brightness;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

import com.mackwu.component.ComponentApp;

/**
 * @author MackWu
 * @since 2022/6/29 15:14
 */
public class BrightnessControl {

    private static BrightnessControl instance;
    private final Context context;
    private static final int MIN_BRIGHTNESS = 30;
    private static final int MAX_BRIGHTNESS = 255;
    private static final int STEP_BRIGHTNESS = 25;

    private BrightnessControl() {
        context = ComponentApp.getInstance().getApplicationContext();
    }

    public static BrightnessControl getInstance() {
        if (instance == null) {
            instance = new BrightnessControl();
        }
        return instance;
    }

    /**
     * 获取当前屏幕亮度值。屏幕亮度值范围（0-255）
     * 命令行：settings get system screen_brightness
     */
    public int getBrightness() {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, MIN_BRIGHTNESS);
    }

    /**
     * 设置屏幕亮度值。
     * 例子：ScreenBrightnessUtil.setScreenBrightness(this, 10);
     * 命令行：settings put system screen_brightness 1
     *
     * @param brightness 统屏幕亮度值。屏幕亮度值范围（0-255）
     */
    public void setBrightness(int brightness) {
        setManualMode();
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, brightness);
    }

    /**
     * 获取最大亮度
     */
    public int getMaxBrightness() {
        return MAX_BRIGHTNESS;
    }

    /**
     * 设置手动调节屏幕亮度。手动调节屏幕亮度模式值为0。
     * 命令行：
     * settings put system screen_brightness_mode 0
     * settings get system screen_brightness_mode
     */
    public void setManualMode() {
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
     * 设置自动调节屏幕亮度。会根据光感自动调节。自动调节屏幕亮度模式值为1。
     * 命令行：
     * settings put system screen_brightness_mode 1
     * settings get system screen_brightness_mode
     */
    public void setAutomaticMode() {
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
     * 设置自动/手动调节屏幕亮度
     */
    public void toggleAutomaticMode(boolean isAutomatic) {
        if (isAutomatic) {
            setAutomaticMode();
        } else {
            setManualMode();
        }
    }

    /**
     * 亮度是否是自动调节。1是自动调节，0是手动调节。
     */
    public boolean isAutomaticMode() {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            int mode = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS_MODE);
            return mode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 增大亮度
     */
    public void brighten() {
        int brightness = getBrightness() + STEP_BRIGHTNESS;
        setBrightness(Math.min(brightness, MAX_BRIGHTNESS));
    }

    /**
     * 减少亮度
     */
    public void dim() {
        int brightness = getBrightness() - STEP_BRIGHTNESS;
        setBrightness(Math.max(brightness, MIN_BRIGHTNESS));
    }

}
