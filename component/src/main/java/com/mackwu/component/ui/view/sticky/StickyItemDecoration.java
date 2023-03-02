package com.mackwu.component.ui.view.sticky;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.mackwu.base.util.Logger;
import com.mackwu.component.ui.view.util.RecyclerViewPositionHelper;

/**
 * @author MackWu
 * @since 2022/11/4 17:01
 * 吸顶布局装饰器
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {

    private final StickyHeaderLayout stickyHeaderLayout;
    private RecyclerView.Adapter<?> adapter;
    private OnStickyChangeListener onStickyChangeListener;

    public StickyItemDecoration(StickyHeaderLayout stickyHeaderLayout) {
        this.stickyHeaderLayout = stickyHeaderLayout;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }
        // 获取第一个可见的item的位置
        int firstVisiblePosition = findFirstVisiblePosition(parent);
        // 获取头部的item的位置
        int headerPosition = findHeaderPosition(firstVisiblePosition);
//        LogUtil.d("onDraw...  firstVisiblePosition=" + firstVisiblePosition + ", headerPosition=" + headerPosition);
        if (firstVisiblePosition >= headerPosition && headerPosition != -1) {
            stickyHeaderLayout.onDataChange(headerPosition);
            /*
             * findChildViewUnder：
             * x=RecyclerView的宽度/2。itemView的宽度需要超过RecyclerView的一半，不然会找不到，或者修改这个x的值。
             * y=浮顶布局高度+0.01f。多加一点高度是为了找到第1个itemView，排查第0个itemView，第0个就是头布局了。
             *
             * 例子：x=1200/2=600 y=147
             */
            float x = c.getWidth() * 1.0f / 2;
            float y = stickyHeaderLayout.getChildHeight() + 0.01f;
//            LogUtil.d("x=" + x + ", y=" + y + ", dp_100=" + parent.getContext().getResources().getDimension(R.dimen.dp_100));
            View itemView = parent.findChildViewUnder(x, y);
            if (itemView == null) {
                Logger.d("child itemView under not found!");
                return;
            }

            /*
             *
             */
            int offset;
            int top = itemView.getTop();
            if (isHeader(parent, itemView) && top > 0) {
                offset = top - stickyHeaderLayout.getChildHeight();
            } else {
                offset = 0;
            }
//            LogUtil.d("firstVisiblePosition=" + firstVisiblePosition + ", headerPosition=" + headerPosition
//                    + ", itemViewPosition=" + parent.getChildAdapterPosition(itemView) + ", isHeader=" + isHeader(parent, itemView) + ", getTop=" + itemView.getTop()
//                    + ", childHeight=" + stickyHeaderLayout.getChildHeight() + ", offset=" + offset);
            Logger.d("getTop=" + itemView.getTop());
            stickyHeaderLayout.alphaChild(offset);
            stickyHeaderLayout.setVisibility(View.VISIBLE);
            if (onStickyChangeListener != null) {
                onStickyChangeListener.onStickyChanged(itemView, isHeader(parent, itemView));
            }
        } else {
            stickyHeaderLayout.reset();
            stickyHeaderLayout.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 获取第一个可见的Item的位置
     *
     * @param recyclerView recyclerView
     */
    private int findFirstVisiblePosition(RecyclerView recyclerView) {
        return RecyclerViewPositionHelper.createHelper(recyclerView).findFirstVisibleItemPosition();
    }

    /**
     * 获取第一个完全可见的Item的位置
     *
     * @param recyclerView recyclerView
     */
    private int findFirstCompletelyVisiblePosition(RecyclerView recyclerView) {
        return RecyclerViewPositionHelper.createHelper(recyclerView).findFirstCompletelyVisibleItemPosition();
    }

    /**
     * 找到头部的位置
     *
     * @param firstVisiblePosition 第一个可见的位置
     */
    private int findHeaderPosition(int firstVisiblePosition) {
        for (int position = firstVisiblePosition; position >= 0; position--) {
            if (isHeader(adapter, position)) {
                return position;
            }
        }
        return -1;
    }

    /**
     * 是否是头部
     *
     * @param adapter  adapter
     * @param position position
     */
    private boolean isHeader(RecyclerView.Adapter<?> adapter, int position) {
        if (adapter instanceof BaseQuickAdapter) {
            SectionEntity sectionEntity = (SectionEntity) ((BaseQuickAdapter<?, ?>) adapter).getItem(position);
            return sectionEntity.isHeader();
        }
        return false;
    }

    private boolean isHeader(RecyclerView recyclerView, int position) {
        return isHeader(recyclerView.getAdapter(), position);
    }

    private boolean isHeader(RecyclerView recyclerView, View view) {
        int position = recyclerView.getChildAdapterPosition(view);
        if (position == RecyclerView.NO_POSITION) {
            return false;
        }
        return isHeader(recyclerView, position);
    }

    public void setOnStickyChangeListener(OnStickyChangeListener onStickyChangeListener) {
        this.onStickyChangeListener = onStickyChangeListener;
    }
}
