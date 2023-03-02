package com.mackwu.component.ui.view.select;

import android.view.View;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author MackWu
 * @since 2022/7/7 16:11
 */
public interface SelectMode<T extends SelectKey> {

    /**
     * 设置全选或者全不选
     *
     * @param isSelectAll 是否全选或者全不选
     */
    void setSelectAll(boolean isSelectAll);

    /**
     * 设置选中
     *
     * @param t 数据
     * @param view view
     */
    void setSelected(T t, View view);

    /**
     * 是否选中
     *
     * @param t 数据
     */
    boolean isSelected(T t);

    /**
     * 获取选中的数据
     */
    @NonNull
    List<T> getSelectedList();

    /**
     * 设置选中状态变化监听
     * @param selectModeChangedListener 选中状态变化监听
     */
    void setSelectModeChangedListener(@NonNull SelectModeChangedListener selectModeChangedListener);

    interface SelectModeChangedListener {

        /**
         * 选中状态变化监听
         * @param isSelectAll true全选，false非全选。注意：非全选包括全不选，和至少一个没选中。
         */
        void onSelectModeChanged(boolean isSelectAll);
    }

}
