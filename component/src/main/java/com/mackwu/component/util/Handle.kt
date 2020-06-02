package com.mackwu.component.util

import android.os.Handler

/**
 * ===================================================
 * Created by MackWu on 2020/5/27 10:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
fun Handler.sendMsg(what: Int, obj: Any){
    val message = obtainMessage()
    message.what = 1
    message.obj = obj
    sendMessage(message)
}