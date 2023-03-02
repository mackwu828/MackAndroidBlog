package com.mackwu.component.ui.view.sticky;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;

/**
 * @author MackWu
 * @since 2022/11/4 17:01
 * 吸顶布局
 */
public class StickyHeaderLayout extends ViewGroup {

    private int offset;
    private int lastOffset = Integer.MIN_VALUE;
    private int lastHeadPosition = Integer.MIN_VALUE;
    private DataCallback dataCallback;

    public StickyHeaderLayout(Context context) {
        this(context, null);
    }

    public StickyHeaderLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyHeaderLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        if (count > 1) {
            throw new IllegalArgumentException("Only support one child view!");
        }
        int desireWidth;
        int desireHeight;
        // 测量子控件的间距
        View child = getChildAt(0);
        measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
        // 测量子控件的宽高
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        desireWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
        desireHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
        desireWidth += getPaddingLeft() + getPaddingRight();
        desireHeight += getPaddingTop() + getPaddingBottom();
        desireWidth = Math.max(desireWidth, getSuggestedMinimumWidth());
        desireHeight = Math.max(desireHeight, getSuggestedMinimumHeight());
        setMeasuredDimension(resolveSize(desireWidth, widthMeasureSpec), resolveSize(desireHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child = getChildAt(0);
        MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int mLeft = paddingLeft + lp.leftMargin;
        int mRight = child.getMeasuredWidth() + mLeft;
        int mTop = paddingTop + lp.topMargin + offset;
        int mBottom = child.getMeasuredHeight() + mTop;
        child.layout(mLeft, mTop, mRight, mBottom);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    public void scrollChild(int offset) {
        if (lastOffset != offset) {
            this.offset = offset;
            ViewCompat.offsetTopAndBottom(getChildAt(0), offset - lastOffset);
        }
        lastOffset = offset;
    }

    public void alphaChild(int offset) {
        float ratio = 1 + offset * 1.0f / getChildHeight();
        if (ratio == 0) {
            ratio = 1;
        }
        getChildAt(0).setAlpha(ratio);
    }

    public int getChildHeight() {
        return getChildAt(0).getHeight();
    }

    public void reset() {
        lastHeadPosition = Integer.MIN_VALUE;
    }

    public void onDataChange(int headPosition) {
        if (dataCallback != null && lastHeadPosition != headPosition) {
            dataCallback.onDataChange(headPosition);
        }
        lastHeadPosition = headPosition;
    }

    public void setDataCallback(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }

    public interface DataCallback {
        void onDataChange(int position);
    }
}
