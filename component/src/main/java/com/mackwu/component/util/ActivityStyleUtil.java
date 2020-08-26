package com.mackwu.component.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * ===================================================
 * Created by MackWu on 2020/7/29 11:24
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public final class ActivityStyleUtil {

    /**
     * 设置非全屏的Activity
     *
     * @param width          Activity宽度
     * @param height         Activity高度
     * @param x              x轴位置
     * @param y              y轴位置
     * @param dimAmount      Activity之外部分透明程度
     * @param isTouchOutside 响应activity对窗口之外的触摸消息
     */
    public static void setNotFullActivity(Activity activity, int width, int height, int x, int y, float dimAmount, boolean isTouchOutside) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = width;
        attributes.height = height;
        attributes.x = x;
        attributes.y = y;
        attributes.dimAmount = dimAmount;
        attributes.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        window.setAttributes(attributes);
    }

    public static void setBottomEndActivity(Activity activity) {
        setNotFullActivity(activity, 100, 100, Gravity.END, Gravity.BOTTOM, 0, true);
    }

}
