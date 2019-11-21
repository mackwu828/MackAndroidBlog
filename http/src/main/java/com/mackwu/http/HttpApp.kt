package com.mackwu.http

import android.app.Application
import com.mackwu.http.okhttp.OkHttpManager

/**
 * ===================================================
 * Created by MackWu on 2019/11/20 15:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class HttpApp: Application() {

    override fun onCreate() {
        super.onCreate()
        OkHttpManager.init(this)
    }
}