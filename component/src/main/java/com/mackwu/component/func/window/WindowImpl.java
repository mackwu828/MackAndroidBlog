package com.mackwu.component.func.window;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.mackwu.base.util.Logger;


/**
 * ===================================================
 * Created by MackWu on 2021/4/19 16:20
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class WindowImpl implements Window, View.OnTouchListener {

    static final String TAG = WindowImpl.class.getSimpleName();
    FloatWindow.Builder builder;
    WindowManager windowManager;
    WindowManager.LayoutParams windowLayoutParams;
    View view;
    boolean isShown = true; // 是否显示

    ValueAnimator animator;

    public WindowImpl(FloatWindow.Builder builder) {
        this.builder = builder;
        // 初始化
        initWindow();

        // 刚开始隐藏
        hide();

        // setOnTouchListener
        if (builder.isMovable) {
            view.setOnTouchListener(this);
        }
    }

    @Override
    public void show() {
        if (isShown) {
            return;
        }
        isShown = true;
        switch (builder.windowDisplay) {
            case SHOW_AND_HIDE:
                view.setVisibility(View.VISIBLE);
                break;
            case ADD_AND_REMOVE:
                windowManager.addView(view, windowLayoutParams);
                break;
            case ONE_PX_AND_MATCH_PARENT:
                break;
            case OUT_OF_SCREEN:
                updateWindowPosition(0, 0, true);
                break;
        }
    }

    @Override
    public void hide() {
        if (!isShown) {
            return;
        }
        isShown = false;
        switch (builder.windowDisplay) {
            case SHOW_AND_HIDE:
                view.setVisibility(View.GONE);
                break;
            case ADD_AND_REMOVE:
                windowManager.removeViewImmediate(view);
                break;
            case ONE_PX_AND_MATCH_PARENT:
                break;
            case OUT_OF_SCREEN:
                updateWindowPosition(-2000, -2000, false);
                break;
        }
    }

//    @Override
//    public void updateXY(int x, int y) {
//        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
//        if (layoutParams instanceof LinearLayout.LayoutParams) {
//            ((LinearLayout.LayoutParams) layoutParams).setMargins(0,0, x, y);
//        }
//        view.setLayoutParams(layoutParams);
//    }

    /**
     * 初始化悬浮窗
     */
    private void initWindow() {
        this.windowManager = (WindowManager) builder.context.getSystemService(Context.WINDOW_SERVICE);
        this.windowLayoutParams = new WindowManager.LayoutParams();
        windowLayoutParams.width = builder.width;
        windowLayoutParams.height = builder.height;
        windowLayoutParams.x = builder.x;
        windowLayoutParams.y = builder.y;
        windowLayoutParams.gravity = builder.gravity;
        windowLayoutParams.type = builder.type;
        windowLayoutParams.flags = builder.flags;
        windowLayoutParams.format = builder.format;
        windowLayoutParams.dimAmount = builder.dimAmount;
        this.view = builder.view;
        windowManager.addView(view, windowLayoutParams);
    }

    /**
     * 更新悬浮窗位置
     *
     * @param x           x
     * @param y           y
     * @param isFocusable isFocusable
     */
    private void updateWindowPosition(int x, int y, boolean isFocusable) {
        windowLayoutParams.x = x;
        windowLayoutParams.y = y;
        windowLayoutParams.flags = getFlag(isFocusable);
        windowManager.updateViewLayout(view, windowLayoutParams);
    }

    private int getFlag(boolean isFocusable) {
        return isFocusable ?
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM |
                        WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS :
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
    }

    float touchStartX, touchStartY;
    float touchCurrentX, touchCurrentY;
    float newX, newY;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStartX = event.getRawX();
                touchStartY = event.getRawY();
                Logger.d("ACTION_DOWN...  touchStartX: " + touchStartX + ", touchStartY: " + touchStartY);
                break;
            case MotionEvent.ACTION_MOVE:
                touchCurrentX = event.getRawX();
                touchCurrentY = event.getRawY();
                newX = windowLayoutParams.x + touchCurrentX - touchStartX;
                newY = windowLayoutParams.y + touchCurrentY - touchStartY;
                Logger.d("ACTION_MOVE...  touchCurrentX: " + touchCurrentX + ", touchCurrentY: " + touchCurrentY);
//                updateXY((int) newX, (int) newY);
                touchStartX = touchCurrentX;
                touchStartY = touchCurrentY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    private void startAnimator() {
        if (animator != null) {
            animator.setDuration(1000).start();
        }
    }

    private void cancelAnimator() {
        if (animator != null) {
            animator.cancel();
        }
    }

}
