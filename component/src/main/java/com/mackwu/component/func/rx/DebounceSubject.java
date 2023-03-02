package com.mackwu.component.func.rx;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * @author MackWu
 * @since 2022/7/22 14:31
 */
public class DebounceSubject<T> {

    private PublishSubject<T> publishSubject;
    private Disposable disposable;

    public void debounce(long timeout, OnDebounceListener<T> listener) {
        publishSubject = PublishSubject.create();
        publishSubject.debounce(timeout, TimeUnit.MILLISECONDS)
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
