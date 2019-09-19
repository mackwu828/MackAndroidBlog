package com.mackwu.kt.base

/**
 * ================================================
 * Created by MackWu on 2019/8/27 11:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * for循环
 */
fun main() {
    // 升序。输出0 1 2 3 4 5 6 7 8 9 10
    for (i in 0..10) {
        print("$i ")
    }
    // 升序，不包括末尾元素。输出0 1 2 3 4 5 6 7 8 9
    for (i in 0 until 10) {
        print("$i ")
    }
    // 降序。输出10 9 8 7 6 5 4 3 2 1 0
    for (i in 10 downTo 0) {
        print("$i ")
    }
    // 自定义歩长。输入0 2 4 6 8 10
    for (i in 0..10 step 2) {
        print("$i ")
    }
    println()


    // 遍历集合
    val list = arrayOf("java", "kotlin", "c")
    // 遍历方法一：0 until list.size
    // 输出java kotlin c
    for (i in 0 until list.size) {
        print(list[i] + " ")
    }
    // 遍历方法二：list.indices
    // 输出java kotlin c
    for (i in list.indices) {
        print(list[i] + " ")
    }
    // 遍历方法三：解构声明
    // 输出java kotlin c
    for (str in list) {
        print("$str ")
    }
    // 输出0java 1kotlin 2c
    for ((i, str) in list.withIndex()) {
        print("$i$str ")
    }

}

