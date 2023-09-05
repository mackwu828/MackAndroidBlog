package com.mackwu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * @author MackWu
 * @since 2020/7/8 15:59
 */
public interface IActivity {

    /**
     * 初始化view
     */
    void initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData(@Nullable Bundle savedInstanceState);

}
