package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.component.databinding.ActivityRxBinding;
import com.mackwu.component.ui.viewmodel.RxViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ===================================================
 * Created by MackWu on 2021/9/27 16:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RxActivity extends BaseActivity<RxViewModel, ActivityRxBinding> {

    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {

        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        viewModel.getData();
    }

}
