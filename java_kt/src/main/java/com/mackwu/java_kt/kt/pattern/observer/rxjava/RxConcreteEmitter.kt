package com.mackwu.java_kt.kt.pattern.observer.rxjava

/**
 * ===================================================
 * Created by MackWu on 2020/1/16 18:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class RxConcreteEmitter(val observer: RxObserver): RxEmitter {

    override fun onNext() {
        observer.onNext()
    }
}