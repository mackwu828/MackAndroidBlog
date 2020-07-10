package com.mackwu.java_kt.kt.pattern.observer.rxjava

/**
 * ===================================================
 * Created by MackWu on 2020/1/16 10:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class RxConcreteObservable : RxObservable(){

    override fun subscribeActual(observer: RxObserver) {
        val emitter = RxConcreteEmitter(observer)
        emitter.onNext()
    }

}

fun main() {
    val rxObservable = RxConcreteObservable()
    val rxObserver = object : RxObserver{
        override fun onNext() {

        }
    }
    rxObservable.subscribe(rxObserver)
}