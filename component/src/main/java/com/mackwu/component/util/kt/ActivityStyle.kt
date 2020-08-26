package com.mackwu.component.util.kt

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.Window
import android.view.WindowManager

/**
 * ===================================================
 * Created by MackWu on 2020/3/11 11:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 设置非全屏的Activity
 * @param width Activity宽度
 * @param height Activity高度
 * @param x
 * @param y
 * @param dimAmount Activity之外部分透明程度
 * @param isTouchOutside 响应activity对窗口之外的触摸消息
 */
fun Activity.setNotFullActivity(width: Int, height: Int, x: Int = 0, y: Int = 0, dimAmount: Float = 0.5f, isTouchOutside: Boolean = false) {
    val attributes = window.attributes
    attributes.width = width
    attributes.height = height
    attributes.x = x
    attributes.y = y
    attributes.dimAmount = dimAmount
    window.attributes = attributes
    if (isTouchOutside) window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
}

/**
 * 设置无标题栏
 */
fun Activity.setNoTitleActivity() {
    requestWindowFeature(Window.FEATURE_NO_TITLE)
}

/**
 * 设置无状态栏
 */
fun Activity.setNoStatusBarActivity() {
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

/**
 * 设置横屏
 */
fun Activity.setHorizontalActivity() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
}

/**
 * 设置竖屏
 */
fun Activity.setVerticalActivity() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
}


fun Activity.setFontScale() {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val configuration = resources.configuration
    configuration.fontScale = 1.0f // normal size
    val metrics = resources.displayMetrics
    wm.defaultDisplay.getMetrics(metrics)
    metrics.scaledDensity = configuration.fontScale * metrics.density
    baseContext.resources.updateConfiguration(configuration, metrics)
}