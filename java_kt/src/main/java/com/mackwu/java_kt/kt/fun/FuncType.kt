package com.mackwu.java_kt.kt.`fun`

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 17:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 函数类型
 * "(参数类型1, 参数类型2) -> 返回类型" 如 val sum: (Int, Int) -> Int // sum变量是函数类型：(Int, Int) -> Int
 *
 * <h2>实例化具有函数类型的变量</h2>
 * 实例化方式一：使用lambda表达式
 * val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
 *
 * 实例化方式二：使用成员引用
 * fun sum(a: Int, b: Int): Int = a + b
 * val sum: (Int, Int) -> Int = ::sum
 *
 * 实例化方式三：使用实现函数类型的类
 * class Sum : (Int, Int) -> Int {
 *      override fun invoke(p1: Int, p2: Int): Int = p1 + p2
 * }
 * val sum: (Int, Int) -> Int = Sum()
 *
 *
 * <h2>调用具有函数类型的变量</h2>
 * 通过 invoke 操作符调用，或者直接调用
 * val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
 * sum.invoke(1, 2)
 * sum(1, 2)
 *
 *
 * <h2>带接收者的函数类型</h2>
 * <h2>类型别名</h2>
 */
fun main() {
    // 函数类型实例化方式一：lambda表达式

    // 函数类型："(参数类型1, 参数类型2) -> 返回类型" (Int, Int) -> Int
    // lambda表达式："{ 参数列表 -> 函数体 }" { a: Int, b: Int -> a + b }
    val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }

    // 函数类型实例化方式二：使用函数、属性、构造函数的引用。::操作符获取成员引用
    fun sum(a: Int, b: Int): Int = a + b

    val sum2: (Int, Int) -> Int = ::sum

    // 函数类型实例化方式三：使用实现函数类型的类
    class Sum : (Int, Int) -> Int {
        override fun invoke(p1: Int, p2: Int): Int = p1 + p2
    }
    val sum3: (Int, Int) -> Int = Sum()


    // 函数类型的值通过 invoke 操作符调用，或者直接调用
    sum.invoke(1, 2)
    sum(1, 2)
}