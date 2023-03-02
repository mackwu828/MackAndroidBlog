package com.mackwu.fastscroll;

/**
 * @author MackWu
 * @since 2023/3/1 18:22
 */
public interface PopupTextHolder {

    /**
     * 设置悬浮文本
     *
     * @param text 文本
     */
    void setPopupText(String text);

    /**
     * 获取悬浮文本
     *
     * @return 文本
     */
    String getPopupText();
}
