package com.mackwu.kt.claz

import com.mackwu.kt.bean.Person

/**
 * ===================================================
 * Created by MackWu on 2019/10/25 18:38
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 无需new直接创建对象
 */
fun Obj1() {
    val person = Person()
}


/**
 * 匿名对象
 */
interface Obj
fun Obj2() {
    val a = object : Obj {

    }
    val b = object {

    }
}


/**
 * 单例对象
 */
object Obj3


/**
 * 伴生对象
 * 直接用类名调用内部的属性和函数
 */
class Obj4{

    companion object {
        const val TAG = "Obj"
    }
}