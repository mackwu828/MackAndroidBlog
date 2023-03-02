package com.mackwu.component.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.component.databinding.ActivityRxBinding;
import com.mackwu.component.ui.viewmodel.RxViewModel;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * ===================================================
 * Created by MackWu on 2021/9/27 16:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RxActivity extends BaseActivity<RxViewModel, ActivityRxBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {


        });
    }

    private void a(){
        Disposable subscribe = Observable.just(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(i -> {

                });
    }

}
