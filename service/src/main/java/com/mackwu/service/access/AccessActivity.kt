package com.mackwu.service.access

import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import com.mackwu.service.R

/**
 * ================================================
 * Created by MackWu on 2019/9/11 10:50
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 */
class AccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 自动开启
        Settings.Secure.putString(contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES, "packageName/serviceName")
        Settings.Secure.putString(contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED, "1")
    }
}