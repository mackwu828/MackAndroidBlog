package com.mackwu.component.func.focus;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.mackwu.component.R;

/**
 * @author MackWu
 * @since 2022/10/31 14:50
 */
public class FocusableLinearLayout extends LinearLayout {

    public FocusableLinearLayout(Context context) {
        this(context, null);
    }

    public FocusableLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FocusableLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        FocusEffectFactory.bindDefault(this);
    }

}
