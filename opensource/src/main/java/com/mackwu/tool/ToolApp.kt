package com.mackwu.tool

import android.app.Application
import com.tencent.bugly.crashreport.CrashReport

/**
 * ================================================
 * Created by MackWu on 2019/9/6 17:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class ToolApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // CrashReport
        CrashReport.initCrashReport(this, "9ca2213327", true)
    }
}