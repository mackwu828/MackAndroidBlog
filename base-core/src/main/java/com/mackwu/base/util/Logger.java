package com.mackwu.base.util;

import android.util.Log;

/**
 * ===================================================
 * Created by MackWu on 2020/6/21 2:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class Logger {

    public static String TAG = "mack_wu";
    public static boolean LOG_DEBUG = true;

    public static void d(String msg) {
        if (LOG_DEBUG) Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (LOG_DEBUG) Log.d(tag, msg);
    }

    public static void e(String msg) {
        if (LOG_DEBUG) Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (LOG_DEBUG) Log.e(tag, msg);
    }

}
