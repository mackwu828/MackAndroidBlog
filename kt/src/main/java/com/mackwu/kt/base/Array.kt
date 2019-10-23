package com.mackwu.kt.base

/**
 * ================================================
 * Created by MackWu on 2019/9/19 17:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 数组
 *
 * <h2>创建数组</h2>
 * 创建数组方式一：库函数[arrayOf]
 * arrayOf(1, 2, 3)
 * arrayOf("java", "kotlin")
 *
 * 创建数组方式二：
 * [intArrayOf] intArrayOf(1, 2, 3)
 * [longArrayOf] longArrayOf(1L, 2L, 3L)
 * [floatArrayOf] floatArrayOf(1f, 2f, 3f)
 * [doubleArrayOf] doubleArrayOf(1.0, 2.02, 3.03333)
 * [booleanArrayOf] booleanArrayOf(false, true)
 *
 * 创建数组方式三：
 * 库函数[arrayOfNulls]用来创建一个指定大小，所有元素都是空的数组
 * arrayOfNulls<Int>(5)
 *
 *
 * <h2>数组赋值</h2>
 * 成员函数get()与set()按照运算符重载约定会转变为[]
 * val intArray = arrayOf(1, 2, 3)
 * intArray[0] = 4
 */
fun main() {
    val intArray = arrayOf(1, 2, 3)
    intArray[0] = 4
}