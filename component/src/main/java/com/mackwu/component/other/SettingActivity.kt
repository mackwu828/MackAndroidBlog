package com.mackwu.component.other

import android.content.Intent
import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.mackwu.component.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/9/10 15:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * Settings
 * Settings就是一个应用，许多的系统属性都是在Settings中设置，如wifi、蓝牙状态、语言、屏幕亮度等。数据主要存储在数据库中
 *
 * <h2>跳转到设置页面</h2>
 * startActivity(Intent(Settings.ACTION_SETTINGS))
 *
 * <h2>获取系统属性</h2>
 * 获取wifi状态：Settings.Secure.getInt(contentResolver, Settings.Secure.WIFI_ON)
 *
 * <h2>添加系统属性</h2>
 * 需要添加系统权限和系统签名
 * 添加wifi状态：Settings.Secure.putString(contentResolver, Settings.Secure.WIFI_ON, "2").toString()
 *
 * <h2>监听系统属性变化</h2>
 * 通过ContentObserver
 * contentResolver.registerContentObserver(Settings.System.getUriFor(SAVE_NAME), true, settingContentObserver)
 */
class SettingActivity : AppCompatActivity() {

    private val settingContentObserver by lazy { SettingContentObserver(Handler()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { startActivity(Intent(Settings.ACTION_SETTINGS)) }
//        // 获取系统属性
//        Settings.Secure.getInt(contentResolver, Settings.Secure.WIFI_ON)
//        // 添加系统属性
//        Settings.Secure.putString(contentResolver, Settings.Secure.WIFI_ON, "2").toString()
//        // 注册监听
//        contentResolver.registerContentObserver(Settings.System.getUriFor(SAVE_NAME), true, settingContentObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        contentResolver.unregisterContentObserver(settingContentObserver)
    }

    inner class SettingContentObserver(handler: Handler) : ContentObserver(handler) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            Toast.makeText(this@SettingActivity, Settings.System.getString(contentResolver, "xxx"), Toast.LENGTH_SHORT).show()
        }
    }

}