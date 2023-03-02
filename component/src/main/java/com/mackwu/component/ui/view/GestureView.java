package com.mackwu.component.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.mackwu.base.util.Logger;

public class GestureView extends View implements GestureDetector.OnGestureListener {
    private static final int verticalMinDistance = 30;
    private static final int minVelocity = 5;
    private GestureDetector gestureDetector;

    public GestureView(Context context) {
        this(context, null);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        gestureDetector = new GestureDetector(getContext().getApplicationContext(), this);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Logger.d("onFling...  " + e1.getY());
        if (e1.getY() - e2.getY() > verticalMinDistance && Math.abs(velocityY) > minVelocity) {
            onFlingUp();
        } else if (e2.getY() - e1.getY() > verticalMinDistance && Math.abs(velocityY) > minVelocity) {
            onFlingDown();
        }
        return false;
    }

    private void onFlingUp() {
    }

    private void onFlingDown() {
    }

    public boolean handlerOnTouchEvent(MotionEvent event) {
        if (gestureDetector == null) {
            gestureDetector = new GestureDetector(getContext().getApplicationContext(), this);
        }
        return gestureDetector.onTouchEvent(event);
    }

}
