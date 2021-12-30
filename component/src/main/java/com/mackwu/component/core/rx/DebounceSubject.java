package com.mackwu.component.core.rx;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * ===================================================
 * Created by MackWu on 2021/11/25 18:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
public class DebounceSubject<T> {

    private PublishSubject<T> publishSubject;
    private Disposable disposable;

    public void debounce(long timeOut, OnDebounceListener<T> listener) {
        publishSubject = PublishSubject.create();
        publishSubject.debounce(timeOut, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull T t) {
                        listener.onDebounce(t);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void onNext(T t) {
        if (publishSubject != null) publishSubject.onNext(t);
    }

    public void dispose() {
        if (disposable != null) disposable.dispose();
    }

    public interface OnDebounceListener<T> {
        void onDebounce(T t);
    }
}
