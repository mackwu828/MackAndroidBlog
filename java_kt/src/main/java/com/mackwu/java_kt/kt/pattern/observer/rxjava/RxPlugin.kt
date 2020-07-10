package com.mackwu.java_kt.kt.pattern.observer.rxjava

/**
 * ===================================================
 * Created by MackWu on 2020/1/16 10:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class RxPlugin {

    companion object {
        @Volatile
        var function: Function? = null

        fun hookObservable(observable: RxObservable): RxObservable {
            function?.apply()
            return observable
        }

        fun hookObserver(observer: RxObserver): RxObserver {
            function?.apply()
            return observer
        }
    }
}