package com.mackwu.kt.pattern.observer

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 14:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class ConcreteObserver : Observer {

    override fun update(o: Observable, data: Any) {
        println(data)
    }
}


fun main() {
    val observable = ConcreteObservable()
    val observer = ConcreteObserver()
    observable.registerObserver(observer)

    observable.setData(1, 2)
    observable.setData(3, 4)
    observable.setData(5, 6)
}