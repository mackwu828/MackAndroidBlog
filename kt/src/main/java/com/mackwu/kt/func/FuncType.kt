package com.mackwu.kt.func

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 17:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 函数类型
 * "(参数类型1, 参数类型2...) -> 返回类型" 如 (Int, Int) -> Int
 *
 * 参数类型可以为空。如() -> Int
 *
 * 可空的函数类型。如 (() -> Unit)?
 *
 * 参数类型是函数类型。如((Int) -> (Int)) -> Unit
 *
 * 返回类型是函数类型。如(Int) -> ((Int) -> Unit)
 *
 * 函数类型接合？ 如(Int) -> (Int) -> Unit
 *
 *
 * <h2>带接收者的函数类型</h2>
 *
 *
 * <h2>类型别名</h2>
 *
 *
 * <h2>函数类型实例化</h2>
 * 函数类型实例化方式一：lambda表达式
 * val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
 *
 * 函数类型实例化方式二：使用函数、属性、构造函数的引用。::操作符获取成员引用
 * fun sum(a: Int, b: Int): Int = a + b
 * val sum: (Int, Int) -> Int = ::sum
 *
 * 函数类型实例化方式三：使用实现函数类型的类
 * class Sum : (Int, Int) -> Int {
 *      override fun invoke(p1: Int, p2: Int): Int = p1 + p2
 * }
 * val sum: (Int, Int) -> Int = Sum()
 *
 *
 * <h2>函数类型调用</h2>
 * 函数类型的值通过 invoke 操作符调用，或者直接调用
 * val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
 * sum.invoke(1, 2)
 * sum(1, 2)
 */
class FuncType

fun main() {
    val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
}