package com.mackwu.component.ui.view.select;

import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/7/11 15:10
 */
public abstract class SelectBaseQuickAdapter<T extends SelectKey, VH extends BaseViewHolder> extends BaseQuickAdapter<T, VH> implements SelectMode<T>, SelectAdapter<T> {

    SelectMode<T> selectMode;

    public SelectBaseQuickAdapter(int layoutResId) {
        super(layoutResId);
        this.selectMode = new SelectModeImpl<>(this);
    }

    @Override
    public void setSelectAll(boolean isSelectAll) {
        selectMode.setSelectAll(isSelectAll);
    }

    @Override
    public void setSelected(T t, View view) {
        selectMode.setSelected(t, view);
    }

    @Override
    public boolean isSelected(T t) {
        return selectMode.isSelected(t);
    }

    @Override
    @NonNull
    public List<T> getSelectedList() {
        return selectMode.getSelectedList();
    }

    @Override
    public void setSelectModeChangedListener(@NonNull SelectMode.SelectModeChangedListener selectModeChangedListener) {
        selectMode.setSelectModeChangedListener(selectModeChangedListener);
    }

}
