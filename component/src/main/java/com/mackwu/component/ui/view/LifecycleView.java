package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2022/11/18 14:04
 */
public class LifecycleView extends View {

    public LifecycleView(Context context) {
        this(context, null);
    }

    public LifecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LifecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Logger.d("onAttachedToWindow...");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Logger.d("onSizeChanged...");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Logger.d("onMeasure...");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Logger.d("onLayout...");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Logger.d("onDraw...");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Logger.d("onDetachedFromWindow...");
    }
}
