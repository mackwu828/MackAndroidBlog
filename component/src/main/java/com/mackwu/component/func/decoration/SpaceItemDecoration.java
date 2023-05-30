package com.mackwu.component.func.decoration;

import android.graphics.Rect;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mackwu.base.util.Logger;

import java.util.Locale;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    // 从右到左
    private final boolean isRlt;
    private static final int OTHER_POSITION = -1;
    private static final int LAST_POSITION = -2;
    private final ArrayMap<Integer, Rect> rectMap = new ArrayMap<>();

    public SpaceItemDecoration() {
        this.isRlt = isRlt();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, RecyclerView parent, @NonNull RecyclerView.State state) {
        // 位置
        int position = parent.getChildAdapterPosition(view);
        // 根据position获取间距
        Rect rect = rectMap.get(position);
        // 获取最后一个位置的间距
        if (position == getLastPosition(parent)) {
            Rect lastRect = rectMap.get(LAST_POSITION);
            if (lastRect != null) {
                rect = lastRect;
            }
        }
        // 获取其他位置的间距
        if (rect == null) {
            rect = rectMap.get(OTHER_POSITION);
        }
        if (rect != null) {
            updateItemOffsets(outRect, rect);
        }
    }

    /**
     * 获取最后一个位置
     *
     * @param parent RecyclerView
     */
    private int getLastPosition(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager == null) {
            return 0;
        }
        int itemCount = layoutManager.getItemCount();
        return itemCount - 1;
    }

    /**
     * 更新间距
     *
     * @param outRect outRect
     * @param rect    rect
     */
    private void updateItemOffsets(Rect outRect, Rect rect) {
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

    /**
     * 设置间距
     */
    public void setRect(int left, int top, int right, int bottom) {
        rectMap.put(OTHER_POSITION, new Rect(left, top, right, bottom));
    }

    /**
     * 设置间距
     */
    public void setRect(int left, int top, int right, int bottom, int position) {
        rectMap.put(position, new Rect(left, top, right, bottom));
    }

    /**
     * 设置最后一个位置间距
     */
    public void setLastRect(int left, int top, int right, int bottom) {
        rectMap.put(LAST_POSITION, new Rect(left, top, right, bottom));
    }

}
