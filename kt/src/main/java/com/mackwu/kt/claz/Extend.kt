package com.mackwu.kt.claz

/**
 * ===================================================
 * Created by MackWu on 2019/10/18 17:58
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 继承
 * "class 类名 : 类名()" 如 class A : B()
 *
 * <h2>类默认不能被继承</h2>
 * 类默认是public final的，所以不能被继承。需要用关键字open声明类，表示可以被继承
 * "open class 类名" 如 open class B
 *
 * <h2>所有类的超类Any</h2>
 * 所有类都有一个共同的超类Any。Any有equals()、hashCode()和toString()三个方法，因此所有类都有这些方法
 *
 * <h2>重写</h2>
 * 类的方法和属性默认都是public final的，所以不能被重写。需要用关键字open声明方法和属性，表示可以被重写。
 * 重写后的方法和属性需要加上override关键字
 *
 * <h2>抽象</h2>
 * 用关键字abstract声明类、方法、属性。抽象类默认可以被继承
 *
 * <h2>接口</h2>
 * 用关键字interface声明接口。在接口中可以声明属性和方法
 */
class A : B(){
    override val text: String = "ExtendA"
    override fun play() {
        super.play()
    }
}

open class B{
    open val text = "ExtendB"
    open fun play(){

    }
}
