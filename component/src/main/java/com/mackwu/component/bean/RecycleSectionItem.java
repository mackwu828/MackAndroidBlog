package com.mackwu.component.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * ===================================================
 * Created by MackWu on 2022/11/3 16:08
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RecycleSectionItem implements SectionEntity {

    boolean isHeader;
    RecycleItem recycleItem;

    public RecycleSectionItem(boolean isHeader, RecycleItem recycleItem) {
        this.isHeader = isHeader;
        this.recycleItem = recycleItem;
    }

    @Override
    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public RecycleItem getRecycleItem() {
        return recycleItem;
    }

    public void setRecycleItem(RecycleItem recycleItem) {
        this.recycleItem = recycleItem;
    }

}
