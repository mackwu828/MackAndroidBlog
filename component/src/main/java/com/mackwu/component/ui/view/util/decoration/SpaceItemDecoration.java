package com.mackwu.component.ui.view.util.decoration;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final Rect rect;
    private Rect firstRect;
    private Rect lastRect;
    private final boolean isRlt;

    public SpaceItemDecoration(Rect rect) {
        this.rect = rect;
        this.isRlt = isRlt();
    }

    public SpaceItemDecoration(int left, int top, int right, int bottom) {
        this.rect = new Rect(left, top, right, bottom);
        this.isRlt = isRlt();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        // 位置
        int position = parent.getChildAdapterPosition(view);
        // 第一个位置
        if (firstRect != null && position == 0) {
            updateItemOffsets(outRect, lastRect);
            return;
        }
        // 最后一个位置
        if (lastRect != null) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager != null) {
                int itemCount = layoutManager.getItemCount();
                if (position == itemCount - 1) {
                    updateItemOffsets(outRect, lastRect);
                    return;
                }
            }
        }
        // 其他位置
        updateItemOffsets(outRect, rect);
    }

    /**
     * 更新间距
     * @param outRect outRect
     * @param rect rect
     */
    private void updateItemOffsets(Rect outRect, Rect rect){
        outRect.top = rect.top;
        if (!isRlt) {
            outRect.left = rect.left;
            outRect.right = rect.right;
        } else {
            outRect.left = rect.right;
            outRect.right = rect.left;
        }
        outRect.bottom = rect.bottom;
    }

    /**
     * 是否从右到左
     */
    private boolean isRlt() {
        return TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == View.LAYOUT_DIRECTION_RTL;
    }

    public void setFirstRect(int left, int top, int right, int bottom) {
        this.firstRect = new Rect(left, top, right, bottom);
    }

    public void setLastRect(int left, int top, int right, int bottom) {
        this.lastRect = new Rect(left, top, right, bottom);
    }
}
