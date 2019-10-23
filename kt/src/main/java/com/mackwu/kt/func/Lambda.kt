package com.mackwu.kt.func

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 17:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Lambda表达式
 * 作为函数的值
 * "{ 参数名称: 参数类型 -> 函数体 }" 如 { a: Int, b: Int -> a + b }
 *
 * 如果函数的最后一个参数是函数，那么lambda表达式可以放在括号外面。
 * fun operation(a: Int, b: Int, operation: (Int, Int) -> Int): Int = operation(a, b)
 * operation(1, 2) { a, b -> a + b }
 *
 * 如果lambda表达式只有一个参数，参数名称不用声明，默认为it
 *
 * 从lambda表达式返回一个值？
 *
 */
class Lambda

fun main() {
    fun operation(a: Int, b: Int, operation: (Int, Int) -> Int): Int = operation(a, b)
    operation(1, 2) { a, b -> a + b }
}

