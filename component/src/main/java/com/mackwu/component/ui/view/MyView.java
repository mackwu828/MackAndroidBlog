package com.mackwu.component.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

import com.mackwu.base.util.Logger;


/**
 * ===================================================
 * Created by MackWu on 2022/2/22 17:47
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyView extends AppCompatImageView {

    Paint paint;
    int screenWidth;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10f);
        paint.setStyle(Paint.Style.STROKE);

        //
        screenWidth = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Logger.d("dispatchTouchEvent...");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            Logger.d("onTouchEvent...  ACTION_UP");
        }
        return super.onTouchEvent(event);
    }
}
