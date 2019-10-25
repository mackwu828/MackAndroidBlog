package com.mackwu.kt.`fun`

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 10:18
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 高阶函数
 * 将函数作为参数或返回值。
 */
fun main() {
    // 两个整数的操作
    fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int = operation(a, b)
    val sum: (Int, Int) -> Int = { a: Int, b: Int -> a + b }
    operate(1, 2, sum)
    operate(1, 2) { a: Int, b: Int -> a + b }
    operate(1, 2) { a: Int, b: Int -> a - b }

    // 点击监听
    fun setOnClickListener(onClick: () -> Unit) = onClick.invoke()
    val onClick: () -> Unit = { println("onClick") }
    setOnClickListener(onClick)
    setOnClickListener { println("onClick") }
}