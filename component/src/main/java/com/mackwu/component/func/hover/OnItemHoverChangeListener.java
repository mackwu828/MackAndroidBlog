package com.mackwu.component.func.hover;

import android.view.MotionEvent;
import android.view.View;

/**
 * @author MackWu
 * @since 2021/11/18 17:29
 */
public interface OnItemHoverChangeListener {

    /**
     * item鼠标变化监听
     *
     * @param v     view
     * @param event event
     */
    void onItemHoverChange(View v, MotionEvent event, int position);

}
