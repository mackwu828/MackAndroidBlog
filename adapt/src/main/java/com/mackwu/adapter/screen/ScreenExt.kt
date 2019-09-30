package com.mackwu.adapter.screen

import android.app.Activity
import android.content.Context

/**
 * ===================================================
 * Created by MackWu on 2019/9/20 16:08
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

// 分辨率：屏幕宽度。单位px
val Activity.screenWidth
        get() = windowManager.defaultDisplay.width

// 分辨率：屏幕高度。单位px
val Activity.screenHeight
    get() = windowManager.defaultDisplay.height

// dpi
val Context.dpi
    get() = resources.displayMetrics.densityDpi

// density
val Context.density
    get() = resources.displayMetrics.density



