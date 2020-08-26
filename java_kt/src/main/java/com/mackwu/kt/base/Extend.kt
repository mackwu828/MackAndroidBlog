package com.mackwu.kt.base

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
open class Animal10{
    open val name = "animal"
    open fun play(){
    }
}
class Dog10 : Animal10(){
    override val name: String = "dog"
    override fun play() {
        println( "$name is playing")
    }
}


/**
 * 如果父类有一个有参数的构造函数，子类需要传递参数
 */
open class Animal11(val name: String)
class Dog11: Animal11("xxx")