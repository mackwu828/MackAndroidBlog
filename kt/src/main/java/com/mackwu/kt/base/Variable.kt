package com.mackwu.kt.base

/**
 * ===================================================
 * Created by MackWu on 2019/10/17 9:53
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 变量
 * 用关键字val声明不可变变量，var声明可变变量
 * "val 变量名: 变量类型 = 变量值" 如 val a: Int = 1
 *
 * <h2>变量类型可以省略</h2>
 * 变量类型可以省略，编译器会自动推断。如 val b = 1 // 类型推断为Int
 * 如果变量初始化时没有赋值，变量类型不能省略。如 val c: Int
 *
 */
fun main() {
    val a: Int = 1

    // 变量类型可以省略，编译器会自动推断
    val b = 1

    // 如果变量初始化时没有赋值，变量类型不能省略
    val c: Int
    c = 1

    // 可变变量
    var d = 1
    d = 2
}
 