package com.mackwu.component.func.decoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author MackWu
 * @since 2023/4/28 16:42
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private final Divider divider;
    private final Paint paint;

    public DividerItemDecoration(Divider divider) {
        this.divider = divider;
        paint = new Paint();
        paint.setColor(divider.getColor());
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (divider.getOrientation() == RecyclerView.VERTICAL) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (divider.getOrientation() == RecyclerView.VERTICAL) {
            outRect.set(0, 0, 0, divider.getHeight());
        } else {
            outRect.set(0, 0, divider.getWidth(), 0);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
//            int left = divider.paddingStart;
//            int top = child.getBottom();
//            int right = parent.getWidth() - divider.paddingEnd;
//            int bottom = top + divider.getHeight();
            // 算上child的上下左右间距
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = divider.paddingStart + layoutParams.leftMargin;
            int top = child.getBottom() + layoutParams.topMargin;
            int right = parent.getWidth() - divider.paddingEnd - layoutParams.rightMargin;
            int bottom = top + divider.getHeight();
            if (i == childCount - 1 && !divider.isLastVisible()) {
                return;
            }
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
//            int left = child.getRight();
//            int top = divider.getPaddingTop();
//            int right = left + divider.getWidth();
//            int bottom = child.getBottom() - divider.getPaddingBottom();
            // 算上child的上下左右间距
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.leftMargin;
            int top = divider.getPaddingTop() + layoutParams.topMargin;
            int right = left + divider.getWidth();
            int bottom = child.getBottom() - divider.getPaddingBottom();
            if (i == childCount - 1 && !divider.isLastVisible()) {
                return;
            }
            canvas.drawRect(left, top, right, bottom, paint);
        }
    }

    public static class Divider {
        private int orientation;
        private int width;
        private int height;
        private int color;
        private int paddingStart;
        private int paddingEnd;
        private int paddingTop;
        private int paddingBottom;
        // 最后一个分割线是否可见
        private boolean isLastVisible;

        public int getOrientation() {
            return orientation;
        }

        public void setOrientation(int orientation) {
            this.orientation = orientation;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getPaddingStart() {
            return paddingStart;
        }

        public void setPaddingStart(int paddingStart) {
            this.paddingStart = paddingStart;
        }

        public int getPaddingEnd() {
            return paddingEnd;
        }

        public void setPaddingEnd(int paddingEnd) {
            this.paddingEnd = paddingEnd;
        }

        public int getPaddingTop() {
            return paddingTop;
        }

        public void setPaddingTop(int paddingTop) {
            this.paddingTop = paddingTop;
        }

        public int getPaddingBottom() {
            return paddingBottom;
        }

        public void setPaddingBottom(int paddingBottom) {
            this.paddingBottom = paddingBottom;
        }

        public boolean isLastVisible() {
            return isLastVisible;
        }

        public void setLastVisible(boolean lastVisible) {
            isLastVisible = lastVisible;
        }
    }

}
