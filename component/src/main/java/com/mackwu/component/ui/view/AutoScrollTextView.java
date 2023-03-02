package com.mackwu.component.ui.view;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2022/10/21 15:39
 */
public class AutoScrollTextView extends AppCompatTextView {

    // 滚动线程
    Runnable scrollRunnable;
    // 当前文本的纵坐标
    int currentY;
    // 每次滚动的间距
    int scrollY = 1;
    // 每次滚动间隔。单位毫秒
    int scrollInterval = 5;
    // 是否可以自动滚动
    boolean canAutoScroll = true;

    public AutoScrollTextView(Context context) {
        this(context, null);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        // setMovementMethod
        setMovementMethod(ScrollingMovementMethod.getInstance());
        // setOnScrollChangeListener
        setOnScrollChangeListener((view, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            currentY = scrollY;
        });
        // scrollRunnable
        scrollRunnable = () -> {
            // 是否可以自动滚动
            if (!canAutoScroll) {
                return;
            }
            // currentY=1001, getTextHeight=1276, getMeasuredHeight=441, getLineHeight=116
//            LogUtil.d("currentY=" + currentY + ", getTextHeight=" + getTextHeight() + ", getMeasuredHeight=" + getMeasuredHeight()
//                    + ", getLineHeight=" + getLineHeight());
            // 如果文本高度小于控件高度，则不滚动。
            if (getTextHeight() <= getMeasuredHeight()) {
                Logger.d("Text height is less than view height.");
                return;
            }
            // 如果滚动到底部，则不再滚动。
            if (currentY >= getTextHeight() - getMeasuredHeight() * 0.8f) {
                Logger.d("View has scrolled to the bottom.");
                return;
            }
            currentY += scrollY;
            scrollTo(0, currentY);
            postDelayed(scrollRunnable, scrollInterval);
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 触屏后停止自动滚动
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            stopAutoScroll();
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        // 重置位置
        scrollTo(0, 0);
        post(this::startAutoScroll);
    }

    /**
     * 开始自动滚动
     */
    public void startAutoScroll() {
        stopAutoScroll();
        canAutoScroll = true;
        postDelayed(scrollRunnable, scrollInterval);
    }

    /**
     * 停止自动滚动
     */
    public void stopAutoScroll() {
        canAutoScroll = false;
        removeCallbacks(scrollRunnable);
    }

    /**
     * 获取文本总高度
     */
    private int getTextHeight() {
        return getLineHeight() * getLineCount();
    }

}
