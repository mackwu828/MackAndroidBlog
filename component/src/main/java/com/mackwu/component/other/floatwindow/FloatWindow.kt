package com.mackwu.component.other.floatwindow

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager

/**
 * ===================================================
 * Created by MackWu on 2020/4/15 17:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class FloatWindow(val context: Context) : IFloatWindow {

    private val windowManager
            get() = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private var floatWindow: View? = null

    fun create(layoutId: Int): FloatWindow = apply { floatWindow = LayoutInflater.from(context.applicationContext).inflate(layoutId, null) }

    override fun show() {
        if (null == floatWindow) return
        WindowManager.LayoutParams().apply {
            // Android8.0悬浮窗类型为 WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY ，8.0以下可以是 WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
            type = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY else WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
            width = WindowManager.LayoutParams.WRAP_CONTENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            gravity = Gravity.START or Gravity.TOP
            x = 0
            y = 0
        }.let { layoutParams ->
            windowManager.addView(floatWindow, layoutParams)
        }
    }

    override fun hide() {

    }

    fun check(activity: Activity, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val packageName = activity.packageName
            activity.startActivityForResult(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")), requestCode)
        } else {

        }
    }
}