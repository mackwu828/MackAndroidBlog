package com.mackwu.kt.`fun`

import com.mackwu.java.pattern.Person


/**
 * ===================================================
 * Created by MackWu on 2019/10/22 17:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * lambda表达式
 * lambda表达式是“函数字面值”，相当于匿名函数或者说是未声明的函数，可以作为表达式传递
 * "{ 参数列表 -> 函数体 }" 如{ a: Int, b: Int -> a + b }
 *
 *
 * <h2>可以赋值给变量</h2>
 * val sum = { a: Int, b: Int -> a + b } // 这时sum具有函数类型
 *
 * <h2>可以传递给函数</h2>
 * fun operation(a: Int, b: Int, operation: (Int, Int) -> Int): Int = operation(a, b) // operation参数是函数类型
 * operation(1, 2, { a: Int, b: Int -> a + b })
 *
 * <h2>如果函数的最后一个参数是函数类型，那么lambda表达式可以放在函数外面</h2>
 * operation(1, 2) { a: Int, b: Int -> a + b }
 *
 * <h2>如果lambda表达式只有一个参数，参数名称不用声明，会隐式声明为it</h2>
 * fun add(a: Int, operation: (Int) -> Int) = operation(a)
 * add(1) { a -> a + 1 }
 * add(1) { it + 1 }
 *
 * <h2>lambda表达式默认返回最后一个表达式的值</h2>
 * operate(1, 2) { a: Int, b: Int -> a + b } // lambda表达式返回的是a + b的值
 * println(operate(1, 2) { a: Int, b: Int ->
 *      a + b
 *      10 // lambda表达式返回10
 * })
 *
 * <h2>Kotlin不支持函数式接口</h2>
 * 函数式接口：只有一个方法的接口
 * 因为 Kotlin 拥有完全的函数类型，如果函数要接收 Lambda 参数，需要使用函数类型，而不是函数式接口
 *
 *
 * <h2>成员引用</h2>
 * 通过::运算符把函数转换为一个值
 * val sum = ::operate
 * sum(1, 2)
 */
fun main() {

    // 可以赋值给变量
    val a = { a: Person -> }
    val sum = { a: Int, b: Int -> a + b }
    sum(1, 2)


    // operation参数是函数类型
    fun operate(a: Int, b: Int, operation: (Int, Int) -> Int): Int = operation(a, b)

    operate(1, 2, sum)

    // 可以传递给函数
    operate(1, 2, { a: Int, b: Int -> a + b })
    // 如果函数的最后一个参数是函数类型，那么lambda表达式可以放在括号外面
    operate(1, 2) { a: Int, b: Int -> a + b }


    // 如果lambda表达式只有一个参数，参数名称不用声明，会隐式声明为it
    fun add(a: Int, operation: (Int) -> Int): Int = operation(a)
    add(1) { a -> a + 1 }
    add(1) { it + 1 }


    // lambda表达式默认返回最后一个表达式的值
    operate(1, 2) { a: Int, b: Int -> a + b } // lambda表达式返回的是a + b的值
    operate(1, 2) { a: Int, b: Int ->
        a + b
        10 // lambda表达式返回10
    }

    // 成员引用
    val sum2 = ::operate
    sum2(1, 2)


    fun start(runnable: Runnable) {
        runnable.run()
    }
    start(object : Runnable {
        override fun run() {

        }
    })
    fun start2(runnable: () -> Unit) = runnable
    start2 {  }
}

// 函数式接口
interface Runnable {
    fun run()
}

