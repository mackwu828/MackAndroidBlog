package com.mackwu.component.ui.view.focus;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mackwu.component.ui.view.recycler.QuickViewHolder;

import java.util.List;


/**
 * @author MackWu
 * @since 2020/8/24 15:45
 */
public abstract class BaseFocusQuickAdapter<T, VH extends QuickViewHolder> extends BaseQuickAdapter<T, VH> {

    private OnItemFocusChangeListener onItemFocusChangeListener;

    public BaseFocusQuickAdapter(@LayoutRes int layoutResId, List<T> data) {
        super(layoutResId, data);
    }

    public BaseFocusQuickAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void bindViewClickListener(@NonNull VH viewHolder, int viewType) {
        super.bindViewClickListener(viewHolder, viewType);
        viewHolder.getItemView().setOnFocusChangeListener((v, hasFocus) -> {
            int position = viewHolder.getItemPosition();
            if (onItemFocusChangeListener != null && position >= 0) {
                onItemFocusChangeListener.onItemFocusChange(v, hasFocus, position);
            }
        });
    }

    public void setOnItemFocusChangeListener(OnItemFocusChangeListener onItemFocusChangeListener) {
        this.onItemFocusChangeListener = onItemFocusChangeListener;
    }

}
