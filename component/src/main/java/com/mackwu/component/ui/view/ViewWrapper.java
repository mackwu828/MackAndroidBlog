package com.mackwu.component.ui.view;

import android.view.View;

/**
 * ===================================================
 * Created by MackWu on 2021/12/9 18:56
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class ViewWrapper {
    private final View target;

    public ViewWrapper(View target) {
        this.target = target;
    }

    public int getWidth() {
        return target.getLayoutParams().width;
    }

    public void setWidth(int width) {
        target.getLayoutParams().width = width;
        target.requestLayout();
    }

    public int getHeight() {
        return target.getLayoutParams().height;
    }

    public void setHeight(int height) {
        target.getLayoutParams().height = height;
        target.requestLayout();
    }
}
