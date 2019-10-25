package com.mackwu.kt.`fun`

/**
 * ===================================================
 * Created by MackWu on 2019/10/15 18:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 函数
 * 用关键字fun声明函数
 * "fun 函数名称(参数名称: 参数类型): 返回值类型 { 函数体 }"
 *     fun sum(a: Int, b: Int): Int {
 *         return a + b
 *     }
 *
 *
 * <h2>函数式编程</h2>
 * 在Kotlin中，函数是一等公民
 * 可以在文件顶层声明函数，不需要依赖类来保存函数(顶层函数)
 * 可以在一个函数内部声明一个函数
 * 函数可以赋值给变量
 * 函数可以作为函数的参数或返回值(高阶函数)
 *
 * <h2>函数式表达式</h2>
 * 如果函数体只有单个表达式，则可以省略{}和return。如fun sum(a: Int, b: Int) = a + b
 *
 * <h2>函数参数可以有默认值</h2>
 * fun sum(a: Int = 1, b: Int = 2) = a + b
 *
 * 如果覆盖的函数有默认值，覆盖后的函数的默认值和父类的函数的默认值相同
 * open class A {
 *      open fun foo(i: Int = 10) { /*……*/ }
 * }
 * class B : A() {
 *      override fun foo(i: Int) { /*……*/ }  // 不能有默认值
 * }
 *
 * <h2>可以在调用函数时使用函数的参数名称，使代码更具有可读性</h2>
 * fun sum(a: Int, b: Int) = a + b 调用时：sum(a = 1, b = 2)
 *
 * 如果函数参数有默认值，不需要使用所有的参数。如fun sum(a: Int = 1, b: Int = 2) = a + b，调用时: sum(b = 2)
 *
 * 如果函数参数有默认值，可以混用位置参数与命名参数，但是位置参数需要在前。如fun sum(a: Int = 1, b: Int = 2) = a + b，调用时: sum(1, b = 2)
 *
 *
 * <h2>可变参数</h2>
 * 用关键字vararg声明可变参数，可变参数是数组
 * fun sum(vararg ts: Int) : Int{
 *      var sum = 0
 *      for (t in ts) {
 *          sum += t
 *      }
 *      return sum
 * }
 * 调用时：sum(1, 2, 3)
 *
 * 调用可变参数的函数时，如果想传入一个数组，可以通过伸展操作符*传入。如
 * val a = intArrayOf(2, 3)
 * sum(1, 2, 3, *a)
 *
 *
 * <h2>返回 Unit 的函数</h2>
 * 如果一个函数不返回任何有用的值，它的返回类型是Unit，可以省略。
 * fun printHello() : Unit {
 *      println("Hello")
 *       // `return Unit` 或者 `return` 是可选的
 * }
 *
 * <h2>中缀表示法</h2>
 * 标有 infix 关键字的函数可以使用中缀表示法（忽略该调用的点与圆括号）调用。
 *
 */
fun main() {
    // 可以在调用函数时使用函数的参数名称，使代码更具有可读性
//    sum2(a = 1, b = 2)


    // 如果函数参数有默认值，不需要使用所有的参数
    sum3()
    // 位置可以混用
    sum3(b = 10, a= 2)
    // 如果函数参数有默认值，可以混用位置参数与命名参数，但是位置参数需要在前
    sum3(10, 11)
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

// 函数式表达式
fun sum2(a: Int, b: Int) = a + b

// 函数参数可以有默认值
fun sum3(a: Int = 1, b: Int = 2) = a + b



 