package com.mackwu.kt.base

import java.io.*

/**
 * ================================================
 * Created by MackWu on 2019/8/27 11:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 控制语句
 *
 *

 *
 *
 * <h2>when</h2>

 *
 *
 * <h2>for循环</h2>
 * @see forTest

 *
 *
 * <h2>for循环遍历集合</h2>

 *
 *
 * <h2>while循环</h2>
 *
 *
 * <h2>跳转</h2>
 * return: 从最近的函数返回
 * break: 终止循环
 * continue: 继续下一次最近的循环
 */

/**
 * if可以作为表达式，赋值给变量或函数。作为表达式时，需要有else分支
 */
fun if1(a: Int, b: Int) {
    val max = if (a > b) a else b
}

/**
 * 表达式函数体
 */
fun if2(a: Int, b: Int): Int = if (a > b) a else b


/**
 * when替代java中的switch，参数可以是任意类型
 */
fun when1(x: Int) {
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
    }

}

/**
 * when可以作为表达式，赋值给变量或函数。作为表达式时，需要有else分支
 */
fun when2(x: Int) {
    val a = when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> print("x is neither 1 nor 2")
    }
}

/**
 * when的分支条件可以用逗号合并
 */
fun when3(x: Int) {
    when (x) {
        1, 2 -> print("x == 1 or x == 2")
        else -> print("x is neither 1 nor 2")
    }
}

/**
 * when的分支条件可以是表达式
 */
fun when4(x: Int) {
    when (x) {
        in 1..10 -> print("x is in 1..10")
        else -> print("none of the above")
    }
}


/**
 * for。升序
 */
fun for1() {
    // 输出0 1 2 3 4 5 6 7 8 9 10
    for (i in 0..10) {
        print("$i ")
    }
}

/**
 * for。升序，不包括末尾元素
 */
fun for2() {
    // 输出0 1 2 3 4 5 6 7 8 9
    for (i in 0 until 10) {
        print("$i ")
    }
}

/**
 * for。升序，自定义歩长
 */
fun for4() {
    // 自定义歩长。输出0 2 4 6 8 10
    for (i in 0..10 step 2) {
        print("$i ")
    }
}

/**
 * for。降序
 */
fun for3() {
    // 输出10 9 8 7 6 5 4 3 2 1 0
    for (i in 10 downTo 0) {
        print("$i ")
    }
}


/**
 * for循环遍历。用 "0 .. list.size -1"、"0 until list.size"，编译器都会提示用结构声明的语法
 */
fun for10() {
    val list = arrayOf("java", "kotlin", "c")
    for (i in 0 until list.size) {
        print(list[i] + " ")
    }
}

/**
 * for循环遍历。根据下标
 */
fun for11() {
    val list = arrayOf("java", "kotlin", "c")
    for (i in list.indices) {
        print(list[i] + " ")
    }
}

/**
 * for循环遍历。解构声明
 */
fun for12() {
    val list = arrayOf("java", "kotlin", "c")
    for (str in list) {
        print("$str ")
    }
    for ((i, str) in list.withIndex()) {
        print("$i$str ")
    }
}

/**
 * while
 */
fun while1() {
    while (true) {

    }
}

/**
 * 与java不同，while循环条件不能用等式。因为在kotlin中等式不是一个表达式。可以用also扩展函数
 */
fun while2(input: InputStream, output: OutputStream) {
    try {
        val buf = ByteArray(1024)
        var len: Int
//        while ((len = fis.read(buf)) != -1)
        while ((input.read(buf).also { len = it }) != -1) {
            output.write(buf, 0, len)
            output.flush()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * while break
 */
fun while3(input: InputStream, output: OutputStream){
    try {
        val buf = ByteArray(1024)
        while (true) {
            val len = input.read(buf)
            if (len <= 0) break
            output.write(buf, 0, len)
            output.flush()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}