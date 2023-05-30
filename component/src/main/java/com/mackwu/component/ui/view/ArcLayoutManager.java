package com.mackwu.component.ui.view;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/3/28 14:34
 */
public class ArcLayoutManager extends RecyclerView.LayoutManager {

    int currentPosition;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        // 移除view
        detachAndScrapAttachedViews(recycler);



        int totalSpace = getWidth() - getPaddingRight();

        //


    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    int radius;


}
