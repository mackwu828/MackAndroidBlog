package com.mackwu.fastscroll.util;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/2/14 16:13
 */
public class RecyclerViewHelper {

    public static int getRows(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            return 0;
        }
        int itemCount = layoutManager.getItemCount();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            return itemCount % spanCount == 0 ? itemCount / spanCount : itemCount / spanCount + 1;
        }
        return itemCount;
    }

    public static int getFirstRow(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            return 0;
        }
        int firstPosition = RecyclerViewPositionHelper.createHelper(recyclerView).findFirstVisibleItemPosition();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            return firstPosition / spanCount;
        }
        return firstPosition;
    }

    public static int getItemHeight(RecyclerView recyclerView, Rect rect) {
        if (recyclerView.getChildCount() == 0) {
            return 0;
        }
        View itemView = recyclerView.getChildAt(0);
        // Returns the bounds of the view including its decoration and margins
        recyclerView.getDecoratedBoundsWithMargins(itemView, rect);
        return rect.height();
    }

}
