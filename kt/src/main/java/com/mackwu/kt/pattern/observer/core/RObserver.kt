package com.mackwu.kt.pattern.observer.core

import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 14:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class RObserver: Observer {
    override fun update(o: Observable, arg: Any) {
        println(arg)
    }
}

//fun main() {
//    val observable = RObservable()
//    observable.addObserver(RObserver())
//
//    observable.setData(1, 2)
//    observable.setData(3, 4)
//}