package com.mackwu.component.ui.view;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.mackwu.base.util.Logger;

/**
 * @author MackWu
 * @since 2023/4/12 14:28
 */
public class SLayoutManager extends RecyclerView.LayoutManager {

    // 正序填充。从开始填充到结束
    public static final int FILL_START_TO_END = 1;
    // 逆序填充。从结束填充到开始
    public static final int FILL_END_TO_START = -1;
    // 填充方向。默认正序填充。
    private int fillDirection = FILL_START_TO_END;
    // 是否逆序
    private boolean reverseLayout;
    // 填充锚点
    int fillAnchor = 0;
    int offset = 0;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public boolean isAutoMeasureEnabled() {
        return true;
    }


    //15:48:34.947  D  fill start...  remainingSpace=1920, mCurrentPosition=0
//            15:48:34.947  D  fill...  remainingSpace=1920, mCurrentPosition=0
//            15:48:34.948  D  fill...  remainingSpace=1715, mCurrentPosition=1
//            15:48:34.949  D  fill...  remainingSpace=1510, mCurrentPosition=2
//            15:48:34.950  D  fill...  remainingSpace=1305, mCurrentPosition=3
//            15:48:34.951  D  fill...  remainingSpace=1100, mCurrentPosition=4
//            15:48:34.952  D  fill...  remainingSpace=895, mCurrentPosition=5
//            15:48:34.953  D  fill...  remainingSpace=690, mCurrentPosition=6
//15:48:34.959  D  fill start...  remainingSpace=1435, mCurrentPosition=0
//            15:48:34.959  D  fill...  remainingSpace=1435, mCurrentPosition=0
//            15:48:34.960  D  fill...  remainingSpace=1230, mCurrentPosition=1
//            15:48:34.961  D  fill...  remainingSpace=1025, mCurrentPosition=2
//            15:48:34.962  D  fill...  remainingSpace=820, mCurrentPosition=3
//            15:48:34.962  D  fill...  remainingSpace=615, mCurrentPosition=4
//            15:48:34.963  D  fill...  remainingSpace=410, mCurrentPosition=5
//            15:48:34.963  D  fill...  remainingSpace=205, mCurrentPosition=6
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
//        if (state.getItemCount() == 0) {
//            removeAndRecycleAllViews(recycler);
//            return;
//        }
//        // 不支持预测动画
//        if (state.isPreLayout()){
//            return;
//        }

        // 设置填充方向
        fillDirection = reverseLayout ? FILL_END_TO_START : FILL_START_TO_END;

        // 轻量级的将view移除屏幕，还是会存在于缓存中
        detachAndScrapAttachedViews(recycler);

        // 重置填充锚点
        fillAnchor = 0;

        // 填充子View
        fill(recycler, state);
    }

    /**
     * 填充子View
     *
     * @param recycler recycler
     * @param state    state
     */
    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Logger.d("fill start...");
        // 剩余空间
        int remainSpace = getTotalSpace();
        // 当前位置
        int currentPosition = 0;
        while (remainSpace > 0 && currentPosition < state.getItemCount()) {
            Logger.d("fill...  currentPosition=" + currentPosition + ", remainSpace=" + remainSpace);
            // 获取子View
            View child = recycler.getViewForPosition(currentPosition);
            currentPosition += fillDirection;
            // 添加子View
            addView(child);
            // 测量子View
            measureChildWithMargins(child, 0, 0);
            // 布局子View
            layoutChunk(child);
            // 计算剩余空间
            remainSpace -= getItemWidth(child) / 2;
        }

        Logger.d("fill completed...  childCount=" + getChildCount() + ", scrapSize=" + recycler.getScrapList().size());
    }

    /**
     * 布局子View
     */
    private void layoutChunk(View child) {
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        // 计算位置
        left = fillAnchor;
        top = getPaddingTop();
        right = left + getItemWidth(child);
        bottom = top + getItemHeight(child) - getPaddingBottom();
        layoutDecoratedWithMargins(child, left, top, right, bottom);
        // 计算下一个View的起始位置
        fillAnchor += getItemWidth(child) / 2;
    }

    private int getItemWidth(View child) {
        return OrientationHelper.createHorizontalHelper(this).getDecoratedMeasurement(child);
//        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//        return getDecoratedMeasuredWidth(child) + params.leftMargin + params.rightMargin;
    }

    private int getItemHeight(View child) {
        return OrientationHelper.createHorizontalHelper(this).getDecoratedMeasurementInOther(child);
//        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//        return getDecoratedMeasuredHeight(child) + params.topMargin + params.bottomMargin;
    }

    private int getTotalSpace() {
        return OrientationHelper.createHorizontalHelper(this).getTotalSpace();
//        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private void logChildCount(RecyclerView.Recycler recycler) {
    }

}
