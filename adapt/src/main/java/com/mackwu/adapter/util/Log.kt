package com.mackwu.adapter.util

import android.util.Log

/**
 * ===================================================
 * Created by MackWu on 2019-05-17 16:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

private const val TAG = "mack adapt"
private const val HEAD = " ### "
//private const val logDebug = BuildConfig.LOG_DEBUG
private const val logDebug = true

fun logD(msg: String) {
    if (logDebug) Log.d(TAG, HEAD + msg)
}

fun logD(tag: String, msg: String) {
    if (logDebug) Log.d(tag, HEAD + msg)
}

fun logE(msg: String) {
    if (logDebug) Log.e(TAG, HEAD + msg)
}

fun logE(tag: String, msg: String) {
    if (logDebug) Log.e(tag, HEAD + msg)
}