package com.mackwu.component.fragment;

import android.view.View;

import com.mackwu.component.R;
import com.mackwu.component.jetpack.MyLifecycleObserver;

/**
 * ===================================================
 * Created by MackWu on 2020/6/20 0:57
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class HomeFragment extends LifecycleFragment {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(final View view) {
        MyLifecycleObserver lifecycleObserver = new MyLifecycleObserver();
        getViewLifecycleOwner().getLifecycle().addObserver(lifecycleObserver);
    }

}
