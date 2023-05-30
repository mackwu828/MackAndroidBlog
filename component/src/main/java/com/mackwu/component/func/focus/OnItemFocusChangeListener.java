package com.mackwu.component.func.focus;

import android.view.View;

/**
 * @author MackWu
 * @since 2021/11/18 17:29
 */
public interface OnItemFocusChangeListener {

    /**
     * item焦点变化监听
     *
     * @param view        view
     * @param hasFocus 是否获取到焦点
     * @param position 位置
     */
    void onItemFocusChange(View view, boolean hasFocus, int position);

}
