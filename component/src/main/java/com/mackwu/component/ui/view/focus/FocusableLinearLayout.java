package com.mackwu.component.ui.view.focus;

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

    FocusProxy focusProxy;

    public FocusableLinearLayout(Context context) {
        this(context, null);
    }

    public FocusableLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FocusableLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        focusProxy = new FocusProxy(this, new FocusEffect() {
            @Override
            public int getBackgroundResource() {
                return R.drawable.selector_focus_stroke;
            }

            @Override
            public int getPadding() {
                return 0;
            }

            @Override
            public void run(View view, boolean gainFocus) {

            }
        });
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (focusProxy != null) {
            focusProxy.onFocusChanged(this, gainFocus);
        }
    }

}
