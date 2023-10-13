package com.mackwu.image.scale.util;

import android.util.Log;

/**
 * @author MackWu
 * @since 2023/9/15 17:12
 */
public final class ScaleLogger {

    public static String TAG = "frame-scale";
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
