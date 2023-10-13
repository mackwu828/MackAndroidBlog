package com.mackwu.base.window;

import static android.view.WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
import static com.mackwu.base.window.WindowDisplayType.OUT_OF_SCREEN;
import static com.mackwu.base.window.WindowDisplayType.SHOW_AND_HIDE;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

/**
 * @author MackWu
 * @since 2021/4/19 16:20
 */
public class WindowImpl implements Window {

    private static final String TAG = WindowImpl.class.getSimpleName();
    private final FloatWindow.Builder builder;
    private WindowManager windowManager;
    private WindowManager.LayoutParams windowLayoutParams;
    private View view;
    private boolean isShown = true;
    private final Runnable hideRunnable;
    private boolean isRemoved;

    public WindowImpl(FloatWindow.Builder builder) {
        this.builder = builder;
        initWindow();
        hide();
        hideRunnable = this::hide;
    }

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

    @Override
    public void show() {
//        Logger.d("show...  isShown=" + isShown + ", isAttachedToWindow=" + view.isAttachedToWindow());
        try {
            if (isShown) {
                return;
            }
            isShown = true;
            switch (builder.displayType) {
                case SHOW_AND_HIDE:
                    view.setVisibility(View.VISIBLE);
                    break;
                case OUT_OF_SCREEN:
                    updateWindowOutOfScreen(0, 0, true);
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAutoHide(long delayMillis) {
        show();
        view.removeCallbacks(hideRunnable);
        view.postDelayed(hideRunnable, delayMillis);
    }

    @Override
    public void hide() {
//        Logger.d("hide...  isShown=" + isShown + ", isAttachedToWindow=" + view.isAttachedToWindow());
        try {
            if (!isShown) {
                return;
            }
            isShown = false;
            switch (builder.displayType) {
                case SHOW_AND_HIDE:
                    view.setVisibility(View.GONE);
                    break;
                case OUT_OF_SCREEN:
                    updateWindowOutOfScreen(-2000, -2000, false);
                    break;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove() {
        try {
            isRemoved = true;
            view.removeCallbacks(hideRunnable);
            windowManager.removeView(view);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新悬浮窗位置
     *
     * @param x           x
     * @param y           y
     * @param isFocusable isFocusable
     */
    private void updateWindowOutOfScreen(int x, int y, boolean isFocusable) {
        windowLayoutParams.x = x;
        windowLayoutParams.y = y;
        int flag = isFocusable ? FLAG_ALT_FOCUSABLE_IM | FLAG_NOT_TOUCH_MODAL : FLAG_NOT_FOCUSABLE;
        windowLayoutParams.flags = flag | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        windowManager.updateViewLayout(view, windowLayoutParams);
    }

}
