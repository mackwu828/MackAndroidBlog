package com.mackwu.xmvc.util;

import android.util.Log;

/**
 * ===================================================
 * Created by MackWu on 2020/6/21 2:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class LogUtil {

    private static final String TAG = "mack_wu";
    public static final boolean LOG_DEBUG = true;

    public static void d(String msg) {
        if (LOG_DEBUG) Log.d(TAG, msg);
    }

    public static void d(String head, String msg) {
        if (LOG_DEBUG) Log.d(TAG, head + " " + msg);
    }

    public static void e(String msg) {
        if (LOG_DEBUG) Log.e(TAG, msg);
    }

    public static void e(String head, String msg) {
        if (LOG_DEBUG) Log.e(TAG, head + " " + msg);
    }
}
