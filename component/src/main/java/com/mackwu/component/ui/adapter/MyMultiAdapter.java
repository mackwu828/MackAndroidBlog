package com.mackwu.component.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.mackwu.component.R;
import com.mackwu.component.bean.RecycleItem;

import java.util.List;
import java.util.Objects;

/**
 * ===================================================
 * Created by MackWu on 2022/2/25 10:37
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class MyMultiAdapter extends BaseDelegateMultiAdapter<RecycleItem, BaseViewHolder> {

    public MyMultiAdapter() {
        setMultiTypeDelegate(new BaseMultiTypeDelegate<RecycleItem>() {
            @Override
            public int getItemType(@NonNull List<? extends RecycleItem> list, int i) {
                return list.get(i).getItemType().getValue();
            }
        });
        Objects.requireNonNull(getMultiTypeDelegate())
                .addItemType(RecycleItem.ItemType.DATE.getValue(), R.layout.layout_item_date)
                .addItemType(RecycleItem.ItemType.IMAGE.getValue(), R.layout.layout_item_image);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == RecycleItem.ItemType.DATE.getValue() ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, RecycleItem item) {
        int itemViewType = helper.getItemViewType();
        // DATE
        if (itemViewType == RecycleItem.ItemType.DATE.getValue()) {
            helper.setText(R.id.tv_date, item.getDate());
        }
        // IMAGE
        else if (itemViewType == RecycleItem.ItemType.IMAGE.getValue()) {
            helper.setImageResource(R.id.iv_image, item.getResId());
        }
    }

}
