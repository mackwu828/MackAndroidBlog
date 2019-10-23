package com.mackwu.kt.claz

/**
 * ===================================================
 * Created by MackWu on 2019/10/18 18:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 伴生对象
 * 用关键字companion object声明伴生对象，直接用类名调用内部的属性和函数，相当于Java的静态成员。如companion object A
 *
 * <h2>可以省略伴生对象的名称</h2>
 * 在这种情况下将使用默认名称Companion。如 companion object
 *
 */
class Comp{
    companion object A{
        fun printHello(){
            println("Hello")
        }
    }
}

fun main() {
    Comp.printHello()
}