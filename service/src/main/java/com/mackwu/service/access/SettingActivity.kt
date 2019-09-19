package com.mackwu.service.access

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.mackwu.service.R

/**
 * ================================================
 * Created by MackWu on 2019/9/10 15:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * Settings
 * Settings就是一个应用，许多的系统属性都是在Settings中设置，如wifi、蓝牙状态、语言、屏幕亮度等。数据主要存储在数据库中
 *
 * <h2>获取系统属性</h2>
 *
 *
 * <h2>添加系统属性</h2>
 * 1.需要添加android:sharedUserId="android.uid.system"获取系统权限
 * 2.需要系统签名。如何添加系统签名
 *
 * <h2>监听系统属性变化</h2>
 */
class SettingActivity : AppCompatActivity() {

    private val settingContentObserver = SettingContentObserver(Handler())

    companion object {
        const val SAVE_NAME = "xxx"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 获取系统属性
//        Settings.Secure.getInt(contentResolver, Settings.Secure.WIFI_ON).toString()

        // 添加系统属性?
//        Settings.Secure.putString(contentResolver, Settings.Secure.WIFI_ON, "2").toString()

        // 注册监听
        contentResolver.registerContentObserver(Settings.System.getUriFor(SAVE_NAME), true, settingContentObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        contentResolver.unregisterContentObserver(settingContentObserver)
    }

    inner class SettingContentObserver(handler: Handler) : ContentObserver(handler) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            Toast.makeText(this@SettingActivity, Settings.System.getString(contentResolver, SAVE_NAME), Toast.LENGTH_SHORT).show()
        }
    }

}