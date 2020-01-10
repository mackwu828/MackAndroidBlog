package com.mackwu.kt.pattern.observer

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 14:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class RealObserver : MyObserver {

    /**
     * 观察者得到数据(状态)后，做相应的处理。
     */
    override fun update(o: MyObservable, data: Any) {
        println(data)
    }
}


//fun main() {
//    val observable = RealObservable()
//    val observer = RealObserver()
//    observable.registerObserver(observer)
//
//    observable.setData(1, 2)
//    observable.setData(3, 4)
//    observable.setData(5, 6)
//}