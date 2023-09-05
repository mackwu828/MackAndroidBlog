package com.mackwu.component.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2022/10/21 15:39
 */
public class AutoScrollView extends ScrollView {

    private static final String TAG = AutoScrollView.class.getSimpleName();
    // 滚动线程
    private Runnable scrollRunnable;
    // 当前文本的纵坐标
    private int currentY;
    // 每次滚动的间距
    private static final int SCROLL_Y = 1;
    // 每次滚动间隔。单位毫秒
    private static final int SCROLL_INTERVAL = 80;
    // 是否可以自动滚动
    private boolean canAutoScroll = true;
    private TextView tvChild;

    public AutoScrollView(Context context) {
        this(context, null);
    }

    public AutoScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {

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
            Logger.d(TAG, "currentY=" + currentY
                    + ", getTextHeight=" + getTextHeight()
                    + ", getMeasuredHeight=" + getMeasuredHeight()
            );
            // 如果文本高度小于控件高度，则不滚动。
            if (getTextHeight() <= getActualHeight()) {
//                Logger.debugWithTag(TAG, "Text height is less than view height.");
                return;
            }
            // 如果滚动到底部，则不再滚动。
            if (currentY > getTextHeight() - getActualHeight()) {
//                Logger.debugWithTag(TAG, "View has scrolled to the bottom.");
                return;
            }
            currentY += SCROLL_Y;
            scrollTo(0, currentY);
            postDelayed(scrollRunnable, SCROLL_INTERVAL);
        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        tvChild = (TextView) getChildAt(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 触屏后停止自动滚动
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            stopAutoScroll();
        }
        return super.onTouchEvent(event);
    }

    private int getActualHeight() {
//        return ((ViewGroup) getParent()).getMeasuredHeight();
        return getMeasuredHeight();
    }

    /**
     * 开始自动滚动
     */
    public void startAutoScroll() {
        stopAutoScroll();
        canAutoScroll = true;
        postDelayed(scrollRunnable, SCROLL_INTERVAL);
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
        return (int) ((tvChild.getLineHeight() + tvChild.getLineSpacingExtra()) * tvChild.getLineCount());
    }

}
