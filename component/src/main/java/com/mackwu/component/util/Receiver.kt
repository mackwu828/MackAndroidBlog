package com.mackwu.component.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 15:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
 fun Context.registerReceiver(receiver: BroadcastReceiver, vararg actions: String){
    val filter = IntentFilter()
    for (action in actions) {
        filter.addAction(action)
    }
    registerReceiver(receiver, filter)
}

fun Context.unregisterReceiver(vararg receivers: BroadcastReceiver){
    for (receiver in receivers) {
        unregisterReceiver(receiver)
    }
}