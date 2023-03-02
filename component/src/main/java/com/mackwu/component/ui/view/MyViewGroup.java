package com.mackwu.component.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2022/7/11 15:23
 */
public class MyViewGroup extends LinearLayout {

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Logger.d("dispatchTouchEvent...");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logger.d("onInterceptTouchEvent...");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logger.d("onTouchEvent...");
        return super.onTouchEvent(event);
    }

}
