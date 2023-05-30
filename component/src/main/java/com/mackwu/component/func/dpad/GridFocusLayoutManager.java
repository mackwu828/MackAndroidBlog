package com.mackwu.component.func.dpad;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/4/13 11:02
 */
public class GridFocusLayoutManager extends GridLayoutManager {

    int focusPosition = RecyclerView.NO_POSITION;

    public GridFocusLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public GridFocusLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public GridFocusLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    public void onFocusChanged(boolean gainFocus) {
        if (gainFocus) {
            for (int i = 0; i < focusPosition; i++) {
                View view = findViewByPosition(i);
                if (view == null) {
                    break;
                }
                if (view.getVisibility() == View.VISIBLE && view.hasFocusable()) {
                    view.requestFocus();
                    break;
                }
            }
        }
    }

    @Nullable
    @Override
    public View onInterceptFocusSearch(@NonNull View focused, int direction) {
        return super.onInterceptFocusSearch(focused, direction);
    }

    @Override
    public boolean onRequestChildFocus(@NonNull RecyclerView parent, @NonNull RecyclerView.State state, @NonNull View child, @Nullable View focused) {
        return super.onRequestChildFocus(parent, state, child, focused);
    }
}
