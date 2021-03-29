package com.mackwu.component.ui.window;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.mackwu.base.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/1/4 10:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseWindow implements IWindow {

    protected Context context;
    protected WindowManager windowManager;
    protected WindowManager.LayoutParams windowLayoutParams;
    protected View view;
    protected Handler handler = new Handler(Looper.getMainLooper());

    public BaseWindow(Context context) {
        this.context = context;
        this.windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.windowLayoutParams = new WindowManager.LayoutParams();
        initView();
    }

    @Override
    public void initView() {
        view = LayoutInflater.from(context).inflate(getLayoutId(), null);
    }

    @Override
    public void show(int width, int height, int type, boolean isFocusable) {
        windowLayoutParams.width = width;
        windowLayoutParams.height = height;
        windowLayoutParams.type = type;
        windowLayoutParams.format = PixelFormat.TRANSLUCENT;
        if (isFocusable) {
            windowLayoutParams.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        } else {
            windowLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }
        LogUtil.d("show...  isAttachedToWindow: " + view.isAttachedToWindow() + ", isShown: " + view.isShown());
        if (!view.isAttachedToWindow() && !view.isShown()) {
            windowManager.addView(view, windowLayoutParams);
        } else {
            windowManager.updateViewLayout(view, windowLayoutParams);
        }
    }

    @Override
    public void showAsApp(int width, int height, boolean isFocusable) {
        int type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            type = WindowManager.LayoutParams.TYPE_APPLICATION;
        }
        show(width, height, type, isFocusable);
    }

    @Override
    public void showAsSystem(int width, int height, boolean isFocusable) {
        show(width, height, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT, isFocusable);
    }

    @Override
    public void hide() {
        if (view.isAttachedToWindow() && view.isShown()) windowManager.removeViewImmediate(view);
    }

}
