package com.mackwu.component.rx;

import android.annotation.SuppressLint;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.PublishSubject;

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
        testMap();
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

    private static void testCreate() {
        // Observable.create
        Observable.create(emitter -> emitter.onNext(1));
//                .subscribe(System.out::println);

        // Observable.just
        Observable.just(1)
                .subscribe(System.out::println);

        // Observable.defer
        // defer返回一个新的Observable
        Observable.defer((Callable<ObservableSource<Integer>>) () -> Observable.just(1))
                .subscribe(System.out::println);
    }

}
