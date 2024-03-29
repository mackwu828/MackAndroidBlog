package com.mackwu.component.func.rx;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableConverter;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * ===================================================
 * Created by MackWu on 2021/8/16 11:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class RxTest {

    public static void main(String[] args) {
//        Observable.just(1)
//                .as(i -> Observable.just("XX"))
//                .subscribe(i -> System.out.println(i));\
    }

    private static void test() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private static void testCreate() {
        // Observable.create
        Observable.create(emitter -> emitter.onNext(1))
                .subscribe(System.out::println);

        // Observable.just
        Observable.just(1)
                .subscribe(System.out::println);

        // Observable.defer
        // defer返回一个新的Observable
        Observable.defer((Callable<ObservableSource<Integer>>) () -> Observable.just(1))
                .subscribe(System.out::println);

        // Observable.interval
        // 定时发送消息
        Observable.interval(1000, TimeUnit.MILLISECONDS)
                .subscribe(System.out::println);
    }

    private static void testMap() {
        // map。将发送的数据转换
        Observable.just(1)
                .map(i -> "XX")
                .subscribe(System.out::println);

        // flatMap。Observable转Observable
        Observable.just(1)
                .flatMap(i -> Observable.just("str" + i))
                .subscribe(System.out::println);
    }

    private static void testFilter() {

    }

}
