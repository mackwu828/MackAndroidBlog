package com.mackwu.component.ui.view.util;

import android.os.SystemClock;

/**
 * @author MackWu
 * @since 2022/7/22 14:31
 */
public final class ThrottleClickUtil {

    // 两次点击的时间间隔
    private final static long DURATION = 800;
    // 上一次点击的时间
    private static long lastClickTime;

    /**
     * 是否快速点击
     */
    public static boolean isThrottleClick() {
        long time = SystemClock.elapsedRealtime();
        if (time - lastClickTime < DURATION) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 是否快速点击
     *
     * @param duration 两次点击的时间间隔
     */
    public static boolean isThrottleClick(long duration) {
        long time = SystemClock.elapsedRealtime();
        if (time - lastClickTime < duration) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    
}
