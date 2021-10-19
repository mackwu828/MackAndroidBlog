package com.mackwu.component.core.service;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.mackwu.component.R;

/**
 * ===================================================
 * Created by MackWu on 2020/9/28 16:39
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class NotificationBar extends FrameLayout {

    private Context context;

    public NotificationBar(Context context) {
        this(context, null);
    }

    public NotificationBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotificationBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.layout_notification_bar, this);
    }

}
