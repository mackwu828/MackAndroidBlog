package com.mackwu.component.util

import android.util.Log

/**
 * ===================================================
 * Created by MackWu on 2019-05-17 16:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

private const val TAG = "mack"

private const val HEAD = " ### "
private val logDebug = true
//private val logDebug = BuildConfig.LOG_DEBUG

fun logD(msg: String) {
    if (logDebug) Log.d(TAG, HEAD + msg)
}

fun logD(head: String, msg: String) {
    if (logDebug) Log.d(TAG, head + HEAD + msg)
}

fun logE(msg: String) {
    if (logDebug) Log.e(TAG, HEAD + msg)
}

fun logE(head: String, msg: String) {
    if (logDebug) Log.d(TAG, head + HEAD + msg)
}