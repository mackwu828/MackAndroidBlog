package com.mackwu.component.func.hover;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.component.func.focus.OnItemFocusChangeListener;
import com.mackwu.component.ui.view.QuickViewHolder;

import java.util.List;


/**
 * @author MackWu
 * @since 2020/8/24 15:45
 */
public abstract class BaseHoverQuickAdapter<T, VH extends QuickViewHolder> extends BaseQuickAdapter<T, VH> {

    private OnItemHoverChangeListener onItemHoverChangeListener;

    public BaseHoverQuickAdapter(@LayoutRes int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public BaseHoverQuickAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void bindViewClickListener(@NonNull VH viewHolder, int viewType) {
        super.bindViewClickListener(viewHolder, viewType);
        viewHolder.getItemView().setOnHoverListener((v, event) -> {
            int position = viewHolder.getItemPosition();
            if (onItemHoverChangeListener != null && position >= 0) {
                onItemHoverChangeListener.onItemHoverChange(v, event, position);
            }
            return false;
        });
    }

    public void setOnItemHoverChangeListener(OnItemHoverChangeListener onItemHoverChangeListener) {
        this.onItemHoverChangeListener = onItemHoverChangeListener;
    }
}
