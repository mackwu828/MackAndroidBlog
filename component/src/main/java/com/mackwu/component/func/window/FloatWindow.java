package com.mackwu.component.func.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.MainThread;

import java.util.HashMap;
import java.util.Map;

/**
 * ===================================================
 * Created by MackWu on 2020/8/3 19:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FloatWindow {

    private static Map<String, Window> windowMap;

    private FloatWindow() {

    }

    @MainThread
    public static Builder with(Context context) {
        return new Builder(context);
    }

    public static Window get(String tag) {
        return windowMap == null ? null : windowMap.get(tag);
    }

    public static class Builder {
        Context context;
        String tag;
        View view;
        int width;
        int height;
        int x;
        int y;
        int gravity;
        int type;
        int flags;
        int format;
        float dimAmount;
        WindowDisplay windowDisplay;
        boolean isMovable;
        boolean isFocusable;

        public Builder(Context context) {
            this.context = context;
            this.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽高全屏
            this.height = WindowManager.LayoutParams.MATCH_PARENT;
            this.gravity = Gravity.CENTER; // 居中。
            this.type = WindowManager.LayoutParams.TYPE_APPLICATION;
            this.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
            this.format = PixelFormat.TRANSLUCENT; // 外部透明。
            this.windowDisplay = WindowDisplay.SHOW_AND_HIDE;
            this.isFocusable = true;
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder view(View view) {
            this.view = view;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder x(int x) {
            this.x = x;
            return this;
        }

        public Builder y(int y) {
            this.y = y;
            return this;
        }

        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        /**
         * 悬浮窗类型。用来确定悬浮窗的层级。
         *
         * @param type 悬浮窗类型
         */
        public Builder type(int type) {
            this.type = type;
            return this;
        }

        /**
         * 悬浮窗flags。用来确定悬浮窗的行为。
         *
         * @param flags {@link WindowManager.LayoutParams#FLAG_NOT_FOCUSABLE} 窗口无法获取焦点，焦点传递给下层窗口。窗口、窗口内部区域和窗口外部区域都可以被点击。
         *              {@link WindowManager.LayoutParams#FLAG_ALT_FOCUSABLE_IM}
         *
         *              {@link WindowManager.LayoutParams#FLAG_NOT_TOUCHABLE} 窗口无法点击。焦点在窗口上，下层窗口无法获取焦点。
         *              {@link WindowManager.LayoutParams#FLAG_NOT_TOUCH_MODAL} 窗口、窗口内部区域和窗口外部区域都可以被点击。默认是设置的。
         *              {@link WindowManager.LayoutParams#FLAG_WATCH_OUTSIDE_TOUCH} 窗口可以收到窗口外部区域的点击事件。
         *
         *              {@link WindowManager.LayoutParams#FLAG_LAYOUT_NO_LIMITS} 窗口的坐标可以设置在屏幕之外。
         *              {@link WindowManager.LayoutParams#FLAG_LAYOUT_IN_SCREEN}
         *
         */
        public Builder flags(int flags) {
            this.flags = flags;
            return this;
        }

        public Builder format(int format) {
            this.format = format;
            return this;
        }

        public Builder dimAmount(float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        public Builder displayType(WindowDisplay windowDisplay) {
            this.windowDisplay = windowDisplay;
            return this;
        }

        public Builder isMovable(boolean isMovable) {
            this.isMovable = isMovable;
            return this;
        }

        /**
         * 是否获取焦点。
         *
         * @param isFocusable 是否获取焦点。true悬浮窗可以被点击和获取焦点，false悬浮窗无法点击和获取焦点
         */
        public Builder isFocusable(boolean isFocusable) {
            this.isFocusable = isFocusable;
            return this;
        }

        public void build() {
            if (windowMap == null) {
                windowMap = new HashMap<>();
            }
            if (windowMap.containsKey(tag)) {
                throw new IllegalArgumentException("Window of this tag has been added, Please set a new tag for the new Window");
            }
            if (view == null) {
                throw new IllegalArgumentException("View has not been set!");
            }
            Window window = new WindowImpl(this);
            windowMap.put(tag, window);
        }
    }

}
