package com.mackwu.http.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.observable.ObservableCreate
import io.reactivex.plugins.RxJavaPlugins


/**
 * ===================================================
 * Created by MackWu on 2019/11/12 14:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

fun main() {
    ob2()
}

/**
 * Observable 和 Observer
 */
fun ob1() {
    val observable = object : Observable<Int>() {
        override fun subscribeActual(observer: Observer<in Int>) {
            observer.onNext(1)
            observer.onNext(2)
            observer.onNext(3)
            observer.onComplete()
        }
    }
    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe...")
        }

        override fun onNext(t: Int) {
            println("onNext...  t: $t")
        }

        override fun onError(e: Throwable) {
            println("onError...")
        }

        override fun onComplete() {
            println("onComplete...")
        }
    }
    // 订阅
    observable.subscribe(observer)
}

/**
 *
 */
fun ob2() {
    val observable = Observable.create(ObservableOnSubscribe<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onComplete()
    })
    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe...")
        }

        override fun onNext(t: Int) {
            println("onNext...  t: $t")
        }

        override fun onError(e: Throwable) {
            println("onError...")
        }

        override fun onComplete() {
            println("onComplete...")
        }
    }
    // 订阅
    observable.subscribe(observer)
}


fun ob3() {
    val observable = ObservableCreate.create<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onComplete()
    }
}