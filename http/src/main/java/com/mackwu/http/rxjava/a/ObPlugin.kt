package com.mackwu.http.rxjava

import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins

/**
 * ===================================================
 * Created by MackWu on 2020/1/16 19:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

fun plugin1(){
    RxJavaPlugins.setOnObservableSubscribe { t1, t2 ->
        object : Observer<Int> {
            override fun onComplete() {
                println("hook onComplete...")
                t2.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                println("hook onSubscribe...")
                t2.onSubscribe(d)
            }

            override fun onNext(t: Int) {
                println("hook onNext...  t: $t")
                t2.onNext(t)
            }

            override fun onError(e: Throwable) {
                t2.onError(e)
            }
        }
    }
}