package com.mackwu.component.core.rx.token;

import android.annotation.SuppressLint;

import com.mackwu.base.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * ===================================================
 * Created by MackWu on 2021/9/30 17:48
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class TokenRequest {

    private static TokenRequest instance;

    private TokenRequest() {
    }

    public static TokenRequest getInstance() {
        if (instance == null) {
            instance = new TokenRequest();
        }
        return instance;
    }

    public void test(){
        new Thread(() -> startRequest(0)).start();
        new Thread(() -> startRequest(1)).start();
    }

    private void startRequest(final int index) {

        // 现获取本地token，如果未空则请求远程token
        Observable.just(1)
                .flatMap(i -> {
                    String token = TokenHolder.getInstance().getToken();
                    if (token == null || token.isEmpty()) {
                        LogUtil.d(index + "token is null!");
                        return Observable.error(new NullTokenException());
                    }
                    return Observable.just(token);
                })

                // token为空，请求
                .retryWhen(observable -> observable.flatMap(throwable -> {
                    if (throwable instanceof NullTokenException) {
                        return TokenLoader.getInstance().getTokenLocked();
                    }
                    return Observable.error(throwable);
                }))

                //
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
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
