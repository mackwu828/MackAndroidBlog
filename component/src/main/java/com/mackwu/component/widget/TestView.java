package com.mackwu.component.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.mackwu.component.databinding.MainActivityBinding;
import com.mackwu.component.ui.MainActivity;

/**
 * ===================================================
 * Created by MackWu on 2021/3/10 15:56
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TestView extends View {

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
    }
}
