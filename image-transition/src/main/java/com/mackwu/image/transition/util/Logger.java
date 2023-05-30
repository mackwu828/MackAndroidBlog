package com.mackwu.image.transition.util;

import android.util.Log;

/**
 * @author MackWu
 * @since 2023/2/1 14:32
 */
public final class Logger {

    public static String TAG = "image-transition";
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
