package com.mackwu.component.bean;

/**
 * ===================================================
 * Created by MackWu on 2022/2/25 10:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RecycleItem {

    private RecycleItem.ItemType itemType = ItemType.IMAGE;
    private String date;
    private int resId;

    public RecycleItem(ItemType itemType, String date) {
        this.itemType = itemType;
        this.date = date;
    }

    public RecycleItem(String date, int resId) {
        this.date = date;
        this.resId = resId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
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

    public enum ItemType {
        DATE(0),

        IMAGE(1),

        ;
        private final int value;

        ItemType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
