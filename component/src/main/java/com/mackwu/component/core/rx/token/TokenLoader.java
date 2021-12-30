package com.mackwu.component.core.rx.token;

import com.mackwu.base.util.LogUtil;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * ===================================================
 * Created by MackWu on 2021/9/27 16:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TokenLoader {

    private static TokenLoader instance;
    private final AtomicBoolean refreshing = new AtomicBoolean(false);
    private final PublishSubject<String> tokenPublishSubject;

    private TokenLoader() {
        tokenPublishSubject = PublishSubject.create();
    }

    public static TokenLoader getInstance() {
        if (instance == null) {
            instance = new TokenLoader();
        }
        return instance;
    }

    public Observable<String> getTokenLocked() {
        LogUtil.d("getTokenLocked...");
        if (refreshing.compareAndSet(false, true)) {
            LogUtil.d("没有请求，发起一次新的token请求");
            Observable.create((ObservableOnSubscribe<String>) e -> {
                Thread.sleep(1000);
                e.onNext("XXX");
            }).doOnNext(token -> {
                LogUtil.d("doOnNext..." + token);
                refreshing.set(false);
                TokenHolder.getInstance().setToken(token);
            }).doOnError(throwable -> {
                LogUtil.d("doOnError..." + throwable);
                refreshing.set(false);
            }).subscribeOn(Schedulers.io())
                    .subscribe(tokenPublishSubject);
        } else {
            LogUtil.d("已经有请求，直接返回等待");
        }
        return tokenPublishSubject;
    }

}
