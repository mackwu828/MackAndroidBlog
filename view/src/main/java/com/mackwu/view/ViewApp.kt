package com.mackwu.view

import android.app.Application
import android.util.Log
import com.tencent.smtt.sdk.QbSdk

/**
 * ===================================================
 * Created by MackWu on 2019/12/9 11:52
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class ViewApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // x5内核
        QbSdk.initX5Environment(this, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                Log.d("TAG", "onCoreInitFinished...")
            }

            override fun onViewInitFinished(p0: Boolean) {
                Log.d("TAG", "onViewInitFinished...")
            }
        })
    }
}