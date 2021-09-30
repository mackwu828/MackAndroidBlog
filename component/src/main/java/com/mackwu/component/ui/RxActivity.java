package com.mackwu.component.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.mackwu.base.BaseActivity;
import com.mackwu.base.util.LogUtil;
import com.mackwu.component.databinding.MainActivityBinding;
import com.mackwu.component.rx.token.NullTokenException;
import com.mackwu.component.rx.token.TokenHolder;
import com.mackwu.component.rx.token.TokenLoader;
import com.mackwu.component.ui.viewmodel.MainViewModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * ===================================================
 * Created by MackWu on 2021/9/27 16:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class RxActivity extends BaseActivity<MainViewModel, MainActivityBinding> {

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        binding.btnTest.setOnClickListener(v -> {
            startRequest(0);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            startRequest(1);
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @SuppressLint("CheckResult")
    private void startRequest(final int index) {
        Observable.just(1)
                .flatMap(i -> {
                    String token = TokenHolder.getInstance().getToken();
                    if (token == null || token.isEmpty()) {
                        LogUtil.d("token is null!");
                        return Observable.error(new NullTokenException());
                    }
                    return Observable.just(token);
                })
                .retryWhen(observable -> observable.flatMap(throwable -> {
                    if (throwable instanceof NullTokenException) {
                        return TokenLoader.getInstance().getTokenLocked();
                    }
                    return Observable.error(throwable);
                }))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        LogUtil.d(index + ":" + "onNext=" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d(index + ":" + "onError=" + e);
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d(index + ":" + "onComplete");
                    }
                });

//        Observable<String> observable = Observable.defer((Callable<ObservableSource<String>>) () -> {
//            String token = TokenHolder.getInstance().getToken();
//            LogUtil.d("获取到缓存Token: " + token);
//            return Observable.just(token);
//        }).flatMap(new Function<String, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(String token) throws Exception {
//                return getUserObservable(index, token);
//            }
//        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
//
//            private int mRetryCount = 0;
//
//            @Override
//            public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
//                return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
//
//                    @Override
//                    public ObservableSource<?> apply(Throwable throwable) throws Exception {
//                        Log.d(index + ":" + "发生错误=" + throwable + ",重试次数=" + mRetryCount);
//                        if (mRetryCount > 0) {
//                            return Observable.error(new Throwable("ERROR_RETRY"));
//                        } else if ("ERROR_TOKEN".equals(throwable.getMessage())) {
//                            mRetryCount++;
//                            return TokenLoader.getInstance().getNetTokenLocked();
//                        } else {
//                            return Observable.error(throwable);
//                        }
//                    }
//                });
//            }
//        });
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
    }

}
