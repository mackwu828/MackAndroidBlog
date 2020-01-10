package com.mackwu.kt.pattern.observer

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 14:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class RealObservable : MyObservable {

    /**
     * 被观察者持有观察者对象。
     */
    private val observers = mutableListOf<MyObserver>()

    override fun registerObserver(observer: MyObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: MyObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers(data: Any) {
        for (observer in observers) {
            observer.update(this, data)
        }
    }

    /**
     * 被观察用来改变数据(状态)。当数据改变时(状态发生变化)，通知所有的观察者。
     */
    fun setData(a: Int, b: Int) {
        notifyObservers(a + b)
    }
}