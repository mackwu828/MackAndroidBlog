package com.mackwu.component.ui.view.util;

import android.view.View;

import com.mackwu.component.ui.view.util.ThrottleClickUtil;

/**
 * @author MackWu
 * @since 2022/7/22 14:31
 */
public class OnThrottleClickListener implements View.OnClickListener {

    View.OnClickListener onClickListener;

    public OnThrottleClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void onClick(View v) {
        if (ThrottleClickUtil.isThrottleClick()) {
            return;
        }
        if (onClickListener != null) onClickListener.onClick(v);
    }
}
