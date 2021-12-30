package com.mackwu.component.base;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.base.recycler.OnItemFocusChangeListener;
import com.mackwu.base.recycler.OnSetDataWhenComputeLayoutListener;

import java.util.List;


/**
 * ===================================================
 * Created by MackWu on 2020/8/24 15:45
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public abstract class BaseFocusQuickAdapter<T, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> {

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
        viewHolder.setIsRecyclable(false);
        viewHolder.itemView.setOnFocusChangeListener((v, hasFocus) -> {
            if (onItemFocusChangeListener != null) {
                int position = viewHolder.getAdapterPosition();
                onItemFocusChangeListener.onItemFocusChanged(v, hasFocus, position);
            }
        });
    }

    public void setOnItemFocusChangeListener(OnItemFocusChangeListener onItemFocusChangeListener) {
        this.onItemFocusChangeListener = onItemFocusChangeListener;
    }

    public void setDataWhenComputeLayout(OnSetDataWhenComputeLayoutListener onSetDataWhenComputeLayoutListener) {
        RecyclerView recyclerView = getRecyclerView();
        if (recyclerView.isComputingLayout()) {
            recyclerView.post(onSetDataWhenComputeLayoutListener::onSetDataWhenComputeLayout);
        } else {
            onSetDataWhenComputeLayoutListener.onSetDataWhenComputeLayout();
        }
    }

}
