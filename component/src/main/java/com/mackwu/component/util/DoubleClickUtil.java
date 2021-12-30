package com.mackwu.component.util;

/**
 * ===================================================
 * Created by MackWu on 2021/11/16 16:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class DoubleClickUtil {

    // 两次点击的时间间隔
    private final static long DURATION = 800;
    // 上一次点击的时间
    private static long lastClickTime;

    /**
     * 是否快速点击
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
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
    public static boolean isFastDoubleClick(long duration) {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < duration) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    
}
