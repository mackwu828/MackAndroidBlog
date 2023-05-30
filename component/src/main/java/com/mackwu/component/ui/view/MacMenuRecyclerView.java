package com.mackwu.component.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/4/6 16:49
 */
public class MacMenuRecyclerView extends RecyclerView {

    public MacMenuRecyclerView(Context context) {
        this(context, null);
    }

    public MacMenuRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MacMenuRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
