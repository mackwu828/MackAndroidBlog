package com.mackwu.component.func.scroll;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.mackwu.fastscroll.util.RecyclerViewPositionHelper;


/**
 * @author MackWu
 * @since 2022/12/13 16:41
 */
public class ScrollUtil {

    /**
     * 是否滚动到底部
     *
     * @param recyclerView recyclerView
     */
    public static boolean isScrollToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || layoutManager.getItemCount() == 0) {
            return false;
        }
        // 当前屏幕可见的item数量
        int visibleItemCount = layoutManager.getChildCount();
        // item总数
        int itemCount = layoutManager.getItemCount();
        // 当前屏幕最后一个可见的item位置
        int lastVisibleItemPosition = com.mackwu.fastscroll.util.RecyclerViewPositionHelper.createHelper(recyclerView).findLastVisibleItemPosition();
        // 当前屏幕最后一个可见的item位置 = item总数 - 1
        return visibleItemCount > 0 && lastVisibleItemPosition == itemCount - 1;
//        // 第一个可见的item位置
//        int firstVisibleItemPosition = RecyclerViewPositionHelper.createHelper(recyclerView).findFirstVisibleItemPosition();
//        return visibleItemCount + firstVisibleItemPosition >= totalItemCount;
    }

    public static boolean isScrollToBottom2(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        return recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange();
    }

    public static boolean isScrollToBottom3(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        return recyclerView.canScrollVertically(1);
    }

    public static boolean isScrollToBottom4(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || layoutManager.getItemCount() == 0) {
            return false;
        }

        // item高度超过屏幕时，判断item高度是否大于RecyclerView高度
        View lastVisibleChild = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
        if (lastVisibleChild != null && lastVisibleChild.getMeasuredHeight() >= recyclerView.getMeasuredHeight()) {
            return !recyclerView.canScrollVertically(1);
        }

        // 根据item高度判断
        int itemCount = layoutManager.getItemCount();
        int lastPosition = com.mackwu.fastscroll.util.RecyclerViewPositionHelper.createHelper(recyclerView).findLastCompletelyVisibleItemPosition();
        View lastChild = layoutManager.getChildAt(lastPosition);

        return false;
    }

    /**
     * 是否滚动到顶部
     *
     * @param recyclerView recyclerView
     */
    public static boolean isScrollToTop(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null || layoutManager.getItemCount() == 0) {
            return false;
        }
        int position = RecyclerViewPositionHelper.createHelper(recyclerView).findFirstVisibleItemPosition();
        View child = recyclerView.getChildAt(0);
        return position == 0 || child.getY() == 0;
    }

}
