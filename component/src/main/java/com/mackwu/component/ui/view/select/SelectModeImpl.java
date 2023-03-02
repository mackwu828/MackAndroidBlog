package com.mackwu.component.ui.view.select;

import android.view.View;

import androidx.annotation.NonNull;

import com.mackwu.base.util.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author MackWu
 * @since 2022/7/8 10:17
 */
public class SelectModeImpl<T extends SelectKey> implements SelectMode<T> {

    public static final String TAG = SelectMode.class.getSimpleName();
    /*
     * 全选中或者全不选。
     * 单选时不改变选中模式。
     * 获取选中列表后设为全不选。
     */
    boolean isSelectAll;
    // 未选中的数据。在全选时使用
    HashMap<String, Boolean> unselectedHashMap = new HashMap<>();
    // 已选中的数据。在非全选时使用
    HashMap<String, Boolean> selectedHashMap = new HashMap<>();
    SelectAdapter<T> adapter;
    SelectModeChangedListener selectModeChangedListener;

    public SelectModeImpl(SelectAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void setSelectAll(boolean isSelectAll) {
        this.isSelectAll = isSelectAll;
        clearSelectMap();
    }

    @Override
    public void setSelected(T t, View view) {
        view.setSelected(!view.isSelected());
        boolean isSelected = view.isSelected();
        Logger.d(TAG, "setSelected...  isSelectAll=" + isSelectAll + ", key=" + t.getKey() + ", isSelected=" + isSelected);
        if (isSelectAll) {
            if (!isSelected) {
                unselectedHashMap.put(t.getKey(), false);
            } else {
                unselectedHashMap.remove(t.getKey());
            }
        } else {
            if (isSelected) {
                selectedHashMap.put(t.getKey(), true);
            } else {
                selectedHashMap.remove(t.getKey());
            }
        }
        if (selectModeChangedListener != null) {
            selectModeChangedListener.onSelectModeChanged(isSelectAll ? unselectedHashMap.size() == 0 : selectedHashMap.size() == adapter.getItemCount());
        }
    }

    @Override
    public boolean isSelected(T t) {
        return isSelectAll ? !unselectedHashMap.containsKey(t.getKey()) : selectedHashMap.containsKey(t.getKey());
    }

    @Override
    @NonNull
    public List<T> getSelectedList() {
        List<T> allList = adapter.getData();
        if (allList == null || allList.isEmpty()) {
            Logger.d(TAG, "getSelectedList...  original list is null");
            return Collections.emptyList();
        }
        List<T> selectedList = isSelectAll ? new ArrayList<>(allList) : new ArrayList<>();
        for (T t : allList) {
            if (isSelectAll) {
                if (unselectedHashMap.containsKey(t.getKey())) {
                    selectedList.remove(t);
                }
            } else {
                if (selectedHashMap.containsKey(t.getKey())) {
                    selectedList.add(t);
                }
            }
        }
        Logger.d(TAG, "getSelectedList...  isSelectAll=" + isSelectAll
                + ", unselectedHashMap.size=" + unselectedHashMap.size() + ", selectedHashMap.size=" + selectedHashMap.size() + ", selectedList.size=" + selectedList.size());
        clearSelectMap();
        isSelectAll = false; // 重置为非全选模式
        return selectedList;
    }

    @Override
    public void setSelectModeChangedListener(@NonNull SelectModeChangedListener selectModeChangedListener) {
        this.selectModeChangedListener = selectModeChangedListener;
    }

    /**
     * 清空map
     */
    private void clearSelectMap() {
//        long startTime = System.currentTimeMillis();
        unselectedHashMap.clear();
        selectedHashMap.clear();
//        LogUtil.d(TAG, "clearSelectMap...  costTime=" + (System.currentTimeMillis() - startTime));
    }

}
