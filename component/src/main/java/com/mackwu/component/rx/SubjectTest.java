package com.mackwu.component.rx;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.mackwu.component.rx.token.NullTokenException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * ===================================================
 * Created by MackWu on 2021/9/30 15:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class SubjectTest {

    public static void main(String[] args) {
//        testAsyncSubject();
        testPublicSubject();
    }

    private static void testReplaySubject() {

    }

    private static void testPublicSubject() {
        // Observer会收到订阅后PublishSubject订阅后发送的所有数据。
        // 无打印
//        PublishSubject<Integer> publishSubject = PublishSubject.create();
//        publishSubject.onNext(1);
//        publishSubject.onNext(2);
//        publishSubject.subscribe(System.out::println);

        // 打印3、4
        PublishSubject<Integer> publishSubject = PublishSubject.create();
        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.subscribe(System.out::println);
        publishSubject.onNext(3);
        publishSubject.onNext(4);
    }

    private static void testBehaviorSubject() {
        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();
    }

    private static void testAsyncSubject() {
        // Observer只会接收AsyncSubject调用onComplete之前发送的最后一个数据。
        // 如果在onComplete之前出现异常，Observer的onError会收到异常信息，且AsyncSubject不再发送任何消息。

        // 不打印
//        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
//        asyncSubject.onNext(1);
//        asyncSubject.onNext(2);
//        asyncSubject.subscribe(System.out::println);
//        asyncSubject.onNext(3);
//        asyncSubject.onNext(4);

        // 只打印4
        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.subscribe(System.out::println);
        asyncSubject.onNext(3);
        asyncSubject.onNext(4);
        asyncSubject.onComplete();

        // 只打印java.lang.NullPointerException
//        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();
//        asyncSubject.onNext(1);
//        asyncSubject.onNext(2);
//        asyncSubject.subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//                System.out.println(integer);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete...");
//            }
//        });
//        asyncSubject.onError(new NullPointerException());
//        asyncSubject.onNext(3);
//        asyncSubject.onNext(4);
//        asyncSubject.onComplete();
    }

}
