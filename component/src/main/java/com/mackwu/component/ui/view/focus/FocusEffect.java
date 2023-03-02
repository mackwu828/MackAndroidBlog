package com.mackwu.component.ui.view.focus;

import android.view.View;

/**
 * @author MackWu
 * @since 2022/10/31 14:56
 */
public interface FocusEffect {

    /**
     * 获取背景。正常背景和焦点态背景
     */
    int getBackgroundResource();

    /**
     * 获取内间距。间距要和 {@link #getBackgroundResource()} 中设置的边框间距保持一致
     */
    int getPadding();

    /**
     * 执行焦点变化时的效果
     *
     * @param view  view
     * @param gainFocus 是否获取焦点
     */
    void run(View view, boolean gainFocus);

}
