package com.mackwu.base.window;


import android.view.WindowManager;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author MackWu
 * @since 2022/9/9 11:20
 */
@IntDef({WindowDisplayType.SHOW_AND_HIDE, WindowDisplayType.OUT_OF_SCREEN,})
@Retention(RetentionPolicy.SOURCE)
public @interface WindowDisplayType {

    /**
     * 显示和隐藏
     */
    int SHOW_AND_HIDE = 0x01;

    /**
     * 移到屏幕外。
     * 需要设置 {@link WindowManager.LayoutParams#FLAG_LAYOUT_NO_LIMITS} 允许悬浮窗的坐标可以设置在屏幕之外。
     */
    int OUT_OF_SCREEN = 0x02;
}
