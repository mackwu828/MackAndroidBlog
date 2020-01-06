package com.mackwu.kt.pattern

import android.view.View
import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 19:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 被观察者
 */
interface MyObservable {
    /**
     * 注册观察者
     */
    fun registerObserver(observer: MyObserver)

    /**
     * 移除观察者
     */
    fun removeObserver(observer: MyObserver)

    /**
     * 通知观察者
     */
    fun notifyObservers(data: Any)
}

/**
 * 观察者
 */
interface MyObserver {
    fun update(o: MyObservable, data: Any)
}


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


/**
 * java.util中提供了一套观察者模式的实现，在使用的时候我们只需要继承Observable和Observer类
 */
class RObservable: Observable(){
    fun setData(a: Int, b: Int){
        setChanged()
        notifyObservers(a + b)
    }
}

class RObserver: Observer{
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


/**
 * OnClickListener是观察者。
 *
 */
interface OnClickListener {
    fun onClick(v: MyView)
}

/**
 * view是被观察者。当点击时(状态发生变化)，去通知所有的OnClickListener，这里只有一个。
 */
class MyView{

    fun setOnClickListener(l: OnClickListener) {
        l.onClick(this)
    }
}

fun main() {
    val myView = MyView()
    myView.setOnClickListener(object : OnClickListener{
        override fun onClick(v: MyView) {

        }
    })
}
