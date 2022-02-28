package com.mackwu.component.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.nio.file.Path;

/**
 * ===================================================
 * Created by MackWu on 2022/2/22 15:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HeartView extends View {

    public HeartView(Context context) {
        this(context, null);
    }

    public HeartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

    }

}
