package com.mackwu.component.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import com.mackwu.base.util.Logger;
import com.mackwu.component.R;

/**
 * ===================================================
 * Created by MackWu on 2022/3/31 15:30
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DynamicHeightImageView extends AppCompatImageView {

    private float heightRatio;

    public DynamicHeightImageView(Context context) {
        this(context, null);
    }

    public DynamicHeightImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicHeightImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(attrs);
    }

    private void initView(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.DynamicHeightImageView);
        heightRatio = typedArray.getFloat(R.styleable.DynamicHeightImageView_height_ratio, 0.75f);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        int width = MeasureSpec.getSize(widthSpec);
        int height = (int) (width * heightRatio);
        Logger.d("getMeasuredWidth=" + getMeasuredWidth() + ", getWidth=" + getWidth()
        + ", width=" + width
        + ", getPaddingLeft=" + getPaddingLeft());
        setMeasuredDimension(width, height);
    }

}
