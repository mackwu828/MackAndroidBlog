package com.mackwu.component.ui.window;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.mackwu.base.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/4/19 16:20
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WindowImpl implements IWindow, View.OnTouchListener {

    private Window.Builder builder;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowLayoutParams;
    private View view;
    private boolean isShown;
    private ValueAnimator mAnimator;
    private float downX;
    private float downY;
    private float upX;
    private float upY;

    private WindowImpl() {

    }

    public WindowImpl(Window.Builder builder) {
        this.builder = builder;
        this.view = builder.view;
        windowManager = (WindowManager) builder.context.getSystemService(Context.WINDOW_SERVICE);
        windowLayoutParams = new WindowManager.LayoutParams();
        windowLayoutParams.width = builder.width;
        windowLayoutParams.height = builder.height;
        windowLayoutParams.x = builder.x;
        windowLayoutParams.y = builder.y;
        windowLayoutParams.gravity = builder.gravity;
        windowLayoutParams.type = builder.type;
        windowLayoutParams.format = builder.format;
        windowLayoutParams.flags = builder.flags;
        windowManager.addView(view, windowLayoutParams);

        // hide
        isShown = true;
        hide();

        //
        if (builder.isMovable) {
            view.setOnTouchListener(this);
        }
    }

    @Override
    public void show() {
        if (isShown) {
            return;
        }
        if (!view.isAttachedToWindow()) {
            windowManager.addView(view, windowLayoutParams);
        }
        isShown = true;
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        if (!isShown) {
            return;
        }
        isShown = false;
        view.setVisibility(View.GONE);
    }

    @Override
    public void remove() {
        if (view.isAttachedToWindow()) windowManager.removeView(view);
    }

    float lastX = 0, lastY = 0, changeX, changeY;
    int newX, newY;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getRawX();
                downY = event.getRawY();
                lastX = event.getRawX();
                lastY = event.getRawY();
                LogUtil.d("downX: " + downX + ", downY: " + downY + ", lastX: " + lastX + ", lastY: " + lastY);
                break;
            case MotionEvent.ACTION_MOVE:
                changeX = event.getRawX() - lastX;
                changeY = event.getRawY() - lastY;
                newX = (int) (view.getX() + changeX);
                newY = (int) (view.getY() + changeY);
                LogUtil.d("changeX: " + downX + ", changeY: " + downY + ", newX: " + lastX + ", newY: " + lastY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }

}
