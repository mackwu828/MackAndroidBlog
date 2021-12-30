package com.mackwu.component.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.leanback.widget.HorizontalGridView;

import com.mackwu.base.util.LogUtil;

/**
 * ===================================================
 * Created by MackWu on 2021/11/17 19:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class FHorizontalGridView extends HorizontalGridView {

    private boolean isFocus;
    public FHorizontalGridView(Context context) {
        super(context);
    }

    public FHorizontalGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FHorizontalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public View focusSearch(View focused, int direction) {
        LogUtil.d("focusSearch...  " + focused.hasFocus() + ", direction: " + direction);
        return super.focusSearch(focused, direction);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        LogUtil.d("onFocusChanged...  gainFocus: " + gainFocus);
    }


}
