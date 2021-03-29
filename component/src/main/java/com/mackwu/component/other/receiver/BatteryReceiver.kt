package com.mackwu.component.other.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.BatteryManager
import androidx.core.app.NotificationCompat
import android.util.Log
import com.mackwu.component.R

/**
 * ================================================
 * Created by MackWu on 2019/9/5 16:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class BatteryReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.run {
            Log.d("TAG", "action: $action")
            // 当前电量
            val level = getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            // 总电量
            val scale = getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            Log.d("TAG", "level: $level, scale: $scale")
            // 充电状态
            val status = getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN)
            when (status) {
                BatteryManager.BATTERY_STATUS_CHARGING -> Log.d("TAG", "充电中")
                BatteryManager.BATTERY_STATUS_DISCHARGING -> Log.d("TAG", "放电中")
                BatteryManager.BATTERY_STATUS_NOT_CHARGING -> Log.d("TAG", "未充电")
                BatteryManager.BATTERY_STATUS_FULL -> Log.d("TAG", "已充满")
                BatteryManager.BATTERY_STATUS_UNKNOWN -> Log.d("TAG", "状态未知")
                else -> {
                }
            }
            // 充电方式
            val plug = getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
            when (plug) {
                BatteryManager.BATTERY_PLUGGED_AC -> Log.d("TAG", "使用充电器充电")
                BatteryManager.BATTERY_PLUGGED_USB -> Log.d("TAG", "使用USB充电")
                BatteryManager.BATTERY_PLUGGED_WIRELESS -> Log.d("TAG", "使用无线方式充电")
                else -> {
                }
            }
//            context.showToast("level: $level, scale: $scale，status: $status, plug: $plug")
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            // 通知
            val notification = NotificationCompat.Builder(context)
                    .setContentTitle("电量变化")
                    .setContentText("level: $level, scale: $scale，status: $status, plug: $plug")
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .build()
            notificationManager.notify(1, notification)
        }
    }
}