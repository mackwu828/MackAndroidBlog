package com.mackwu.http.other

import android.app.Application
import com.mackwu.http.okhttp.OkHttpManager
import com.mackwu.http.okhttp_retrofit_rxjava.RetrofitManager

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
        RetrofitManager.init(this)
        com.mackwu.http.other.okhttp_retrofit_coroutines.RetrofitManager.init(this)
    }
}