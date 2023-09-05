package com.mackwu.component.func.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2020/11/4 17:38
 * 行：row
 * 列：column、span
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    // 列数
    private final int spanCount;
    // 行间距
    private final int rowSpacing;
    // 列间距
    private final int spanSpacing;
    // 第一行间距。指上间距。
    private int firstRowSpacing = -1;
    // 最后一行间距。指下间距
    private int lastRowSpacing = -1;

    /**
     * @param spanCount   列数
     * @param rowSpacing  行间距
     * @param spanSpacing 列间距
     */
    public GridSpaceItemDecoration(int spanCount, int rowSpacing, int spanSpacing) {
        this.spanCount = spanCount;
        this.rowSpacing = rowSpacing;
        this.spanSpacing = spanSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // 位置
        int position = parent.getChildAdapterPosition(view);
        // 如果是网格布局，且只有一列的情况只需设置行间距
        if (isOnlyOneSpan(outRect, parent, position)) {
            return;
        }
        // 当前位置所在列
        int spanIndex = position % spanCount;
        // 左间距
        outRect.left = spanIndex * spanSpacing / spanCount;
        // 右间距
        outRect.right = spanSpacing - (spanIndex + 1) * spanSpacing / spanCount;
        // 行间距
        outRect.bottom = rowSpacing;
        // 第一行上间距、最后一行下间距
        if (parent.getLayoutManager() != null) {
            int itemCount = parent.getLayoutManager().getItemCount();
            // 行数
            int rows = itemCount % spanCount == 0 ? itemCount / spanCount : itemCount / spanCount + 1;
            // 当前行
            int currentRow = position / spanCount;
            // 设置第一行上间距
            if (currentRow == 0 && firstRowSpacing != -1) {
                outRect.top = firstRowSpacing;
            }
            // 设置最后一行下间距
            else if (currentRow == rows - 1 && lastRowSpacing != -1) {
                outRect.bottom = lastRowSpacing;
            }
        }
    }

    /**
     * 如果是网格布局，且只有一列的情况只需设置行间距
     * @param outRect outRect
     * @param parent parent
     * @param position position
     */
    private boolean isOnlyOneSpan(Rect outRect, RecyclerView parent, int position) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager.SpanSizeLookup spanSizeLookup = ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            if (spanSizeLookup.getSpanSize(position) == spanCount) {
                // 行间距
                outRect.bottom = rowSpacing;
                // 设置第一行上间距
                if (position == 0 && firstRowSpacing != -1) {
                    outRect.top = firstRowSpacing;
                }
                // 设置最后一行下间距
                else if (position == layoutManager.getItemCount() - 1 && lastRowSpacing != -1) {
                    outRect.bottom = lastRowSpacing;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 设置第一行间距。指上间距
     *
     * @param firstRowSpacing 第一行间距
     */
    public void setFirstRowSpacing(int firstRowSpacing) {
        this.firstRowSpacing = firstRowSpacing;
    }

    /**
     * 设置最后一行间距。指下间距
     *
     * @param lastRowSpacing 最后一行间距
     */
    public void setLastRowSpacing(int lastRowSpacing) {
        this.lastRowSpacing = lastRowSpacing;
    }

}
