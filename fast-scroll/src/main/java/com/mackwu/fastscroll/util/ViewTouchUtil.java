package com.mackwu.fastscroll.util;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/2/8 15:27
 */
public class ViewTouchUtil {

    public static boolean isInViewTouchArea(View view, float x, float y) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        return y >= top && y <= bottom && x >= left && x <= right;
    }

    public static boolean isInViewTouchArea(RecyclerView recyclerView, View view, float x, float y) {
        int scrollX = recyclerView.getScrollX();
        int scrollY = recyclerView.getScrollY();
        int viewLeft = view.getLeft() - scrollX;
        int viewRight = view.getRight() - scrollX;
        int viewTop = view.getTop() - scrollY;
        int viewBottom = view.getBottom() - scrollY;
        return (x >= viewLeft && x < viewRight) || (y >= viewTop && y < viewBottom);
    }

}
