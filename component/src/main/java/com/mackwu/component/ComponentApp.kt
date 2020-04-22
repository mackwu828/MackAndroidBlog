package com.mackwu.component

import android.app.Application
import com.mackwu.component.other.db.StudentDao

/**
 * ===================================================
 * Created by MackWu on 2019/11/18 14:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class ComponentApp : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this

        //
        StudentDao.init(this)
    }

    companion object {
        lateinit var app: ComponentApp
    }
}