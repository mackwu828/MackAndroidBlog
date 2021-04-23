package com.mackwu.component.ui.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
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
public class Window {

    private static Map<String, IWindow> windowMap;
    private static Builder builder;

    private Window() {

    }

    @MainThread
    public static Builder with(Context context) {
        return builder = new Builder(context);
    }

    public static IWindow get(String tag) {
        return windowMap == null ? null : windowMap.get(tag);
    }

    public static class Builder {
        Context context;
        String tag;
        int layoutId;
        View view;
        int width;
        int height;
        int x;
        int y;
        int gravity;
        int type;
        int format;
        int flags;
        boolean isMovable;

        public Builder(Context context) {
            this.context = context;
            this.gravity = Gravity.CENTER; // 居中。
            this.type = WindowManager.LayoutParams.TYPE_APPLICATION;
            this.format = PixelFormat.TRANSLUCENT; // 外部透明。
            this.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH; // 窗口可以点击，外部也可以点击。
        }

        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        public Builder layoutId(@LayoutRes int layoutId) {
            this.layoutId = layoutId;
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

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder format(int format) {
            this.format = format;
            return this;
        }

        public Builder flags(int flags) {
            this.flags = flags;
            return this;
        }

        public Builder isMovable(boolean isMovable) {
            this.isMovable = isMovable;
            return this;
        }

        public void build() {
            if (windowMap == null) {
                windowMap = new HashMap<>();
            }
            if (windowMap.containsKey(tag)) {
                throw new IllegalArgumentException("Window of this tag has been added, Please set a new tag for the new Window");
            }
            if (view == null && layoutId == 0) {
                throw new IllegalArgumentException("View has not been set!");
            }
            if (view == null) {
                view = LayoutInflater.from(context).inflate(layoutId, null);
            }
            IWindow window = new WindowImpl(this);
            windowMap.put(tag, window);
        }
    }

}
