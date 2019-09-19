package com.mackwu.kt.base

/**
 * ================================================
 * Created by MackWu on 2019/9/19 17:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 数组Array
 *
 */
fun main() {

    // 创建数组方法一：arrayOf
    val intArray = arrayOf(1, 2, 3)
    val strArray = arrayOf("java", "kotlin")
    // 创建数组方法二：intArrayOf、floatArrayOf...
    intArrayOf(1, 2, 3)
    longArrayOf(1L, 2L, 3L)
    floatArrayOf(1f, 2f, 3f)
    doubleArrayOf(1.0, 2.02, 3.03333)
    booleanArrayOf(false, true, false)



    // 指定数组长度创建数组方法一：
    val array = Array(5) { it }
    array[0] = 1
    // 指定数组长度创建数组方法二：可空数组
    val arrayOfNulls = arrayOfNulls<Int>(5)
    arrayOfNulls[0] = null

}