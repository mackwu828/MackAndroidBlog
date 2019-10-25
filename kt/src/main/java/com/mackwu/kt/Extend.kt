package com.mackwu.kt

/**
 * ===================================================
 * Created by MackWu on 2019/10/18 17:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 类默认是public final的，需要用关键字open声明类，表示可以被继承
 */
open class Animal
class Dog : Animal()


/**
 * 如果类没有显示继承一个类，那么他隐式继承了Any
 */
class Animal2 // 隐式继承了Any
class Animal3 : Any()


/**
 * 类的方法和属性默认都是public final的，需要用关键字open声明方法和属性，表示可以被重写。
 * 重写后的方法和属性需要加上override关键字
 */
class Dog10 : Animal10(){
    override val name: String = "dog"
    override fun play() {
        println( "$name is playing")
    }
}
open class Animal10{
    open val name = "animal"
    open fun play(){
    }
}
