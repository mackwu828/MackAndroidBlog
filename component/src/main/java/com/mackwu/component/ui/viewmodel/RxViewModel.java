package com.mackwu.component.ui.viewmodel;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.mackwu.base.util.LogUtil;
import com.mackwu.base.viewmodel.BaseViewModel;
import com.mackwu.component.util.RxUtil;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.lifecycle.LifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * ===================================================
 * Created by MackWu on 2021/10/8 11:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RxViewModel extends BaseViewModel {

    @SuppressLint("AutoDispose")
    public void getData() {
        LogUtil.d("getData...");
//        Observable.timer(15000, TimeUnit.MILLISECONDS)
////                .as(AutoDispose.autoDisposable(this))
//                .subscribe(new Observer<Long>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Long i) {
//                        LogUtil.d("onNext: " + i);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        LogUtil.d("onError: " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        LogUtil.d("onComplete!");
//                    }
//                });
    }

}
