package com.mackwu.http.rxjava

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

/**
 * ===================================================
 * Created by MackWu on 2020/1/15 17:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * Flowable 和 Subscriber
 */
fun fl() {
    Flowable.create(FlowableOnSubscribe<Int> { emitter ->
        emitter.onNext(1)
        emitter.onNext(2)
        emitter.onNext(3)
        emitter.onComplete()
    }, BackpressureStrategy.LATEST).subscribe(object : Subscriber<Int> {

        override fun onSubscribe(s: Subscription?) {
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