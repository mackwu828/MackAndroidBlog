package com.mackwu.adapter.version.install

import android.content.Context

/**
 * ===================================================
 * Created by MackWu on 2019/12/31 10:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object InstallFactory {

    fun <T : ApkInstall> createInstaller(context: Context, installClass: Class<T>): T {
        val constructor = installClass.getDeclaredConstructor(Context::class.java)
        constructor.isAccessible = true
        return constructor.newInstance(context)
    }

}