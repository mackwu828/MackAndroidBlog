package com.mackwu.component;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mackwu.component.jetpack.UserLiveData;
import com.mackwu.xmvc.BaseFragment;

/**
 * ===================================================
 * Created by MackWu on 2020/6/19 23:43
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class JetpackFragment extends BaseFragment {

    private Button btnLifeCycle;
    private UserLiveData userLiveData;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_jetpack;
    }

    @Override
    public void initView(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

}
