package com.mackwu.base.fragment;

import androidx.fragment.app.FragmentActivity;

/**
 * @author MackWu
 * @since 2020/7/31 17:23
 */
public interface IDialogFragment extends IFragment {

    /**
     * 宽
     */
    int getWidth();

    /**
     * 高
     */
    int getHeight();

    /**
     * 透明度
     */
    float getDimAmount();

    /**
     * 权重
     */
    int getGravity();

    /**
     * 遥控返回键是否可以取消
     */
    boolean isCancelable();

    /**
     * 点击外部区域是否可以取消
     */
    boolean isCanceledOnTouchOutside();

    /**
     * 显示
     */
    void show(FragmentActivity activity);
}
