package com.mackwu.kt.util

import android.util.Log

/**
 * ===================================================
 * Created by MackWu on 2019-05-17 16:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

private const val TAG = "accu-services"
private const val HEAD = " ### "
//private val logDebug = BuildConfig.LOG_DEBUG
private val logDebug = true


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
    if (logDebug) Log.d(tag, HEAD + msg)
}