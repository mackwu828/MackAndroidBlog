package com.mackwu.http.rxjava

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.observable.ObservableCreate


/**
 * ===================================================
 * Created by MackWu on 2019/11/12 14:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 *
 */
fun obCreate1() {
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

    /**
     * 链式
     */
    Observable.create(ObservableOnSubscribe<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onComplete()
    }).subscribe(object : Observer<Int> {

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
    })
}


fun obCreate2() {
    val observable = ObservableCreate.create<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onComplete()
    }
}