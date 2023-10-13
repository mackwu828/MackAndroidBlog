package com.mackwu.base.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;

import java.util.HashMap;
import java.util.Map;

/**
 * @author MackWu
 * @since 2020/8/3 19:23
 */
public class FloatWindow {

    private static final Map<String, Window> windowMap = new HashMap<>();

    private FloatWindow() {

    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    /**
     * 根据TAG获取Window
     *
     * @param tag tag
     * @return Window
     */
    public static Window get(String tag) {
        return windowMap.get(tag);
    }

    /**
     * 销毁Window
     *
     * @param tag tag
     */
    public static void destroy(String tag) {
        Window window = windowMap.get(tag);
        if (window != null) {
            window.remove();
            windowMap.remove(tag);
        }
    }

    public static class Builder {
        Context context;
        String tag;
        View view;
        int width;
        int height;
        int gravity;
        int x;
        int y;
        int type;
        int flags;
        int format;
        float dimAmount;
        int displayType;

        public Builder(Context context) {
            this.context = context;
            this.width = WindowManager.LayoutParams.WRAP_CONTENT;
            this.height = WindowManager.LayoutParams.WRAP_CONTENT;
            this.gravity = Gravity.CENTER;
            this.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            this.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
            this.format = PixelFormat.TRANSLUCENT;
            this.displayType = WindowDisplayType.SHOW_AND_HIDE;
        }

        /**
         * 标识
         *
         * @param tag 标识
         */
        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        /**
         * 布局Id
         *
         * @param layoutId 布局Id
         */
        public Builder layoutId(@LayoutRes int layoutId) {
            this.view = LayoutInflater.from(context).inflate(layoutId, null);
            return this;
        }

        /**
         * view
         *
         * @param view view
         */
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

        public Builder gravity(int gravity) {
            this.gravity = gravity;
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

        /**
         * 悬浮窗类型。用来确定悬浮窗的层级。
         *
         * @param type 悬浮窗类型
         *             {@link WindowManager.LayoutParams#TYPE_PHONE} android6.0~8.0需要动态申请权限。android8.0以上包括8.0需要系统权限。
         *             {@link WindowManager.LayoutParams#TYPE_APPLICATION_OVERLAY} android8.0以上需要动态申请权限。否则会抛出异常。permission denied for window type 2038
         *             {@link WindowManager.LayoutParams#TYPE_SYSTEM_ALERT} 需要系统权限。
         *             <p>
         *             悬浮窗层级：TYPE_SYSTEM_ALERT > TYPE_APPLICATION_OVERLAY
         */
        public Builder type(int type) {
            this.type = type;
            return this;
        }

        /**
         * 悬浮窗flags。用来确定悬浮窗的行为。
         *
         * @param flags {@link WindowManager.LayoutParams#FLAG_NOT_TOUCH_MODAL} 窗口和窗口外部区域，按键事件和触屏事件都可以获取焦点。默认设置。
         *              {@link WindowManager.LayoutParams#FLAG_NOT_FOCUSABLE} 窗口按键事件和触屏事件都获取不到焦点。
         *              {@link WindowManager.LayoutParams#FLAG_NOT_TOUCHABLE} 窗口按键事件可以获取焦点，但是触屏事件获取不到焦点。
         *              {@link WindowManager.LayoutParams#FLAG_ALT_FOCUSABLE_IM}
         *              {@link WindowManager.LayoutParams#FLAG_WATCH_OUTSIDE_TOUCH} 窗口可以获取到窗口外部区域的触屏事件。
         *              {@link WindowManager.LayoutParams#FLAG_LAYOUT_NO_LIMITS} 窗口的坐标可以设置在屏幕之外。
         *              {@link WindowManager.LayoutParams#FLAG_LAYOUT_IN_SCREEN} 放置在整个屏幕之内,无视其他的装饰(比如状态栏)。
         *              {@link WindowManager.LayoutParams#FLAG_FULLSCREEN} 全屏显示，隐藏其他的装饰(比如状态栏)。
         *              {@link WindowManager.LayoutParams#FLAG_SHOW_WALLPAPER}
         */
        public Builder flags(int flags) {
            this.flags = flags;
            return this;
        }

        public Builder dimAmount(float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        /**
         * 显示方式
         *
         * @param displayType see {@link  WindowDisplayType}
         */
        public Builder displayType(@WindowDisplayType int displayType) {
            this.displayType = displayType;
            return this;
        }

        public void build() {
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
