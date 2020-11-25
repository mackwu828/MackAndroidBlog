package com.mackwu.component.util;

import android.content.Context;
import android.provider.Settings;
import android.view.Surface;

/**
 * ===================================================
 * Created by MackWu on 2020/10/29 16:07
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * https://stackoverflow.com/questions/14587085/how-can-i-globally-force-screen-orientation-in-android/14862852#14862852
 */
public class RotationUtil {

    /**
     * 禁止屏幕自动旋转。0禁止，1允许
     * <p>
     * 命令行：
     * settings put system accelerometer_rotation 1
     * settings get system accelerometer_rotation
     */
    public static void disableAutoRotation(Context context) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 0);
    }

    /**
     * 允许屏幕自动旋转。
     */
    public static void enableAutoRotation(Context context) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, 1);
    }

    /**
     * 是否允许屏幕自动旋转。
     *
     * @param isEnable true允许，false禁止
     */
    public static void toggleAutoRotation(Context context, boolean isEnable) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.ACCELEROMETER_ROTATION, isEnable ? 1 : 0);
    }


    /**
     * 锁定屏幕当前状态。0：0° 1：90° 2:180° 3:270°
     * 注1：需要先禁止屏幕自动旋转。
     * 注2：如果屏幕默认是竖屏的，则0则是锁定为竖屏。如果屏幕默认是横屏的，则0则是锁定为横屏。
     * <p>
     * 命令行：
     * settings put system user_rotation 0
     * settings get system user_rotation
     */
    public static void lockRotation0(Context context) {
        disableAutoRotation(context);
        Settings.System.putInt(context.getContentResolver(), Settings.System.USER_ROTATION, Surface.ROTATION_0);
    }

}
