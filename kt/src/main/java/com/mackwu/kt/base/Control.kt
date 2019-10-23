package com.mackwu.kt.base

/**
 * ================================================
 * Created by MackWu on 2019/8/27 11:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 控制语句
 *
 *
 * <h2>if</h2>
 * if可以作为表达式，即它会返回一个值，可以赋值给变量或函数。if作为表达式时，需要有else分支
 * val max = if(a > b) a else b
 * @see ifTest1
 * fun max(a: Int, b: Int): Int = if (a > b) a else b
 * @see ifTest2
 *
 *
 * <h2>when</h2>
 * when取代了switch操作符，when的参数可以是任意类型
 * when也可以作为表达式。when作为表达式时，需要有else分支
 * when的分支条件可以用逗号合并
 * when的分支条件可以是表达式
 * @see whenTest
 *     when (x) {
 *          1 -> print("x == 1")
 *          2 -> print("x == 2")
 *          else -> print("x is neither 1 nor 2")
 *      }
 *
 *
 * <h2>for循环</h2>
 * @see forTest
 * 升序。
 * for (i in 0..10) { print("$i ") } // 输出0 1 2 3 4 5 6 7 8 9 10
 * 升序，不包括末尾元素。
 * for (i in 0 until 10) { print("$i ") } // 输出0 1 2 3 4 5 6 7 8 9
 * 降序。
 * for (i in 10 downTo 0) { print("$i ") } // 输出10 9 8 7 6 5 4 3 2 1 0
 * 自定义歩长。
 * for (i in 0..10 step 2) { print("$i ") } // 输出0 2 4 6 8 10
 *
 *
 * <h2>for循环遍历集合</h2>
 * val list = arrayOf("java", "kotlin", "c")
 * 遍历方式一：
 * for (i in 0 until list.size) { print(list[i] + " ") } // 输出java kotlin c
 * 遍历方式二：
 * for (i in list.indices) { print(list[i] + " ") } // 输出java kotlin c
 * 遍历方法三：解构声明
 * for (str in list) { print("$str ") } // 输出java kotlin c
 * for ((i, str) in list.withIndex()) { print("$i$str ") } // 输出0java 1kotlin 2c
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
fun main() {
    forTest()
}


/**
 * if
 */
private fun ifTest1(a: Int, b: Int) {
    val max = if (a > b) a else b
}

private fun ifTest2(a: Int, b: Int): Int = if (a > b) a else b


/**
 * when
 */
fun whenTest(x: Int) {
    when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
    }

    val a = when (x) {
        1 -> print("x == 1")
        2 -> print("x == 2")
        else -> print("x is neither 1 nor 2")
    }

    when (x) {
        1, 2 -> print("x == 1 or x == 2")
        else -> print("x is neither 1 nor 2")
    }

    when (x) {
        in 1..10 -> print("x is in 1..10")
        else -> print("none of the above")
    }
}


/**
 * for
 */
private fun forTest() {
    val list = arrayOf("java", "kotlin", "c")
    for (str in list) {
        if ("kotlin" == str) {
            return
        }
        print("$str ")
    }
    println()
    print("end...")
}
