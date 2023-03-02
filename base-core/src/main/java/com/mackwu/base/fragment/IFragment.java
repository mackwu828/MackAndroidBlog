package com.mackwu.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

public interface IFragment {

    /**
     * 初始化view
     */
    void initView(@Nullable Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    void initData(@Nullable Bundle savedInstanceState);

}
