package com.mackwu.component.ui.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author MackWu
 * @since 2023/4/6 17:45
 */
public class MacMenuLayout extends LinearLayout {

    ObjectAnimator enterAnimator;

    public MacMenuLayout(Context context) {
        this(context, null);
    }

    public MacMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MacMenuLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_HOVER_ENTER) {
//            float factor = 1.65f;
//            PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1, factor);
//            PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1, factor);
//            enterAnimator = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY)
//                    .setDuration(150);
//            enterAnimator.start();
//        }
//        // ACTION_HOVER_MOVE
//        else if (event.getAction() == MotionEvent.ACTION_HOVER_MOVE) {
//            if (enterAnimator != null) {
//                enterAnimator.reverse();
//            }
//        }
        return super.onHoverEvent(event);
    }

}
