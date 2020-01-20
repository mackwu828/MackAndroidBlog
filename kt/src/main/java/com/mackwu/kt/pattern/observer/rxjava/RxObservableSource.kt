package com.mackwu.kt.pattern.observer.rxjava

/**
 * ===================================================
 * Created by MackWu on 2020/1/16 10:22
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface RxObservableSource {

    fun subscribe(observer: RxObserver)
}