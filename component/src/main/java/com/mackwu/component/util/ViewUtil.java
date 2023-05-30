package com.mackwu.component.util;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author MackWu
 * @since 2023/4/7 17:20
 */
public class ViewUtil {

    public static boolean isInViewTouchArea(View view, float x, float y) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        return y >= top && y <= bottom && x >= left && x <= right;
    }

    public static boolean isInViewTouchArea(View scrollView, View view, float x, float y) {
        int scrollX = scrollView.getScrollX();
        int scrollY = scrollView.getScrollY();
        int viewLeft = view.getLeft() - scrollX;
        int viewRight = view.getRight() - scrollX;
        int viewTop = view.getTop() - scrollY;
        int viewBottom = view.getBottom() - scrollY;
        return (x >= viewLeft && x < viewRight) || (y >= viewTop && y < viewBottom);
    }

    public static View findViewAtPosition(View parent, int x, int y) {
        if (parent instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) parent;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                View viewAtPosition = findViewAtPosition(child, x, y);
                if (viewAtPosition != null) {
                    return viewAtPosition;
                }
            }
            return null;
        } else {
            Rect rect = new Rect();
            parent.getGlobalVisibleRect(rect);
            if (rect.contains(x, y)) {
                return parent;
            } else {
                return null;
            }
        }
    }

}
