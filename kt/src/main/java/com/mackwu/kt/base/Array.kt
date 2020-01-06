package com.mackwu.kt.base

/**
 * ================================================
 * Created by MackWu on 2019/9/19 17:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 数组
 */
fun array1() {
    val intArray = arrayOf(1, 2, 3)
    val longArray = arrayOf(1L, 2L, 3L)
    val floatArray = arrayOf(1f, 2f, 3f)
    val strArray = arrayOf("java", "kotlin")
}

fun array2(){
    val intArray = intArrayOf(1, 2, 3)
    val longArray = longArrayOf(1L, 2L, 3L)
    val floatArray = floatArrayOf(1f, 2f, 3f)
}

/**
 * 指定数组大小。
 */
fun array10(){
    val intArray = IntArray(10)
}

/**
 * 指定数组大小。可空
 */
fun array11(){
    val arrayOfNulls = arrayOfNulls<Int>(10)
}

/**
 * 成员函数get()与set()按照运算符重载约定会转变为[]
 */
fun array20(){
    val intArray = arrayOf(1, 2, 3)
    intArray[0] = 4
}