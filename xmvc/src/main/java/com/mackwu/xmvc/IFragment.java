package com.mackwu.xmvc;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface IFragment {

    /**
     * layout id
     */
    @LayoutRes
    int getLayoutId();

    /**
     * init view
     */
    void initView(@NonNull View view, @Nullable Bundle savedInstanceState);

    /**
     * init data
     */
    void initData(@Nullable Bundle savedInstanceState);
}
