package com.mackwu.component.func.window;

import android.view.WindowManager;

/**
 * ===================================================
 * Created by MackWu on 2021/7/27 14:55
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public enum WindowDisplay {

    /**
     * 显示和隐藏
     */
    SHOW_AND_HIDE,

    /**
     * 添加和移除。
     * 注：频繁的添加和移除会内存泄漏
     */
    ADD_AND_REMOVE,

    /**
     * 1px和全屏
     */
    ONE_PX_AND_MATCH_PARENT,

    /**
     * 移到屏幕外。
     * 需要设置 {@link WindowManager.LayoutParams#FLAG_LAYOUT_NO_LIMITS} 允许悬浮窗的坐标可以设置在屏幕之外。
     */
    OUT_OF_SCREEN,
}
