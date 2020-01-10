package com.mackwu.kt.pattern.observer.core

import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 14:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * java.util中提供了一套观察者模式的实现，在使用的时候我们只需要继承Observable和Observer类
 */
class RObservable: Observable(){
    fun setData(a: Int, b: Int){
        setChanged()
        notifyObservers(a + b)
    }
}