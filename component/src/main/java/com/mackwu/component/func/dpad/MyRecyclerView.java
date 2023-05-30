package com.mackwu.component.func.dpad;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/4/13 14:23
 */
public class MyRecyclerView extends RecyclerView {

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (getLayoutManager() instanceof GridFocusLayoutManager) {
            ((GridFocusLayoutManager) getLayoutManager()).onFocusChanged(gainFocus);
        }
    }

}
