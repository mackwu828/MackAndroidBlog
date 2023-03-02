package com.mackwu.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * ===================================================
 * Created by MackWu on 2020/7/8 15:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
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
