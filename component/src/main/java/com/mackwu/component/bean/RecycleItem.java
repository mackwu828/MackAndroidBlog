package com.mackwu.component.bean;

import com.chad.library.adapter.base.entity.JSectionEntity;

/**
 * ===================================================
 * Created by MackWu on 2022/2/25 10:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RecycleItem extends JSectionEntity {

    private String date;
    private int resId;
    private boolean isHeader;

    public RecycleItem() {
    }

    public RecycleItem(String date, int resId) {
        this.date = date;
        this.resId = resId;
    }

    public RecycleItem(String date, int resId, boolean isHeader) {
        this.date = date;
        this.resId = resId;
        this.isHeader = isHeader;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public boolean isHeader() {
        return isHeader;
    }
}
