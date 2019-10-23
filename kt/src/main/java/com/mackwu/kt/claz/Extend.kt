package com.mackwu.kt.claz

/**
 * ===================================================
 * Created by MackWu on 2019/10/18 17:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 继承
 * "class 类名 : 类名()" 如 class Student : Person()
 *
 * 注1：类默认是public final的，即默认不能被继承。需要用关键字open声明类，表示可以被继承
 * "open class 类名" 如 open class Person
 * 注2：所有类都有一个共同的超类Any。Any有equals()、hashCode()和toString()三个方法，因此所有类都有这些方法
 *
 *
 * <h2>重写</h2>
 * 类的方法和属性默认都是public final的，即不能被重写。需要用关键字open声明方法和属性，表示可以被重写。
 * 重写后的方法和属性需要加上override关键字
 *
 */

class ExtendA : ExtendB(){
    override val text: String = "ExtendA"
    override fun play() {
        super.play()
    }
}

open class ExtendB{
    open val text = "ExtendB"
    open fun play(){

    }
}
