package com.mackwu.kt.pattern.observer

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 14:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 观察者
 */
interface Observer {

    /**
     * 观察者得到数据(状态)后，做相应的处理。
     */
    fun update(o: Observable, data: Any)
}