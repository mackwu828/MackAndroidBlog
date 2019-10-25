package com.mackwu.component.receiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R

/**
 * ================================================
 * Created by MackWu on 2019/9/5 15:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 耳机的电量能实时更新，通知到notification栏
 *
 * <h2>测试</h2>
 * 手机插入USB充电
 * 2019-09-05 15:54:39.195 9014-9014/com.mackwu.receiver D/BatteryActivity: level: 77, scale: 100
 * 2019-09-05 15:54:39.195 9014-9014/com.mackwu.receiver D/BatteryActivity: 充电中
 * 2019-09-05 15:54:39.195 9014-9014/com.mackwu.receiver D/BatteryActivity: 使用USB充电
 */
class BatteryActivity : AppCompatActivity() {

    private val batteryReceiver = BatteryReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryReceiver)
    }
}