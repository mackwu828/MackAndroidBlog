package com.mackwu.component.util

import android.util.Log
import com.mackwu.component.BuildConfig

/**
 * ===================================================
 * Created by MackWu on 2019-05-17 16:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

private const val TAG = "mack"

private const val HEAD = TAG + "_" + BuildConfig.VERSION_CODE + " ### "
//private const val HEAD = BuildConfig.FLAVOR + "_" + BuildConfig.VERSION_CODE + " ### "

private val logDebug = true
//private val logDebug = BuildConfig.LOG_DEBUG


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