package com.mackwu.xmvc;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
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
     * layout id
     */
    @LayoutRes
    int getLayoutId();

    /**
     * init view
     */
    void initView(@Nullable Bundle savedInstanceState);

    /**
     * init data
     */
    void initData(@Nullable Bundle savedInstanceState);
}
