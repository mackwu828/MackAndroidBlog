package com.mackwu.kt.func

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
 * <h2>函数参数可以有默认值</h2>
 * 默认值通过类型后面的 = 及给出的值来定义。如fun sum(a: Int = 1, b: Int = 2) = a + b
 *
 * 如果覆盖的函数有默认值，覆盖后的函数的默认值和父类的函数的默认值相同
 * open class A {
 *      open fun foo(i: Int = 10) { /*……*/ }
 * }
 * class B : A() {
 *      override fun foo(i: Int) { /*……*/ }  // 不能有默认值
 * }
 *
 * 如果一个默认参数在一个无默认值的参数之前，那么该默认值只能通过使用命名参数调用该函数来使用？
 *
 * 如果在默认参数之后的最后一个参数是 lambda 表达式，那么它既可以作为命名参数在括号内传入，也可以在括号外传入？
 *
 *
 * <h2>命名参数</h2>
 * 可以在调用函数时使用函数的参数名称，使代码更具有可读性。如fun sum(a: Int, b: Int) = a + b 调用时：sum(a = 1, b = 2)
 *
 * 如果函数参数有默认值，不需要使用所有的参数。如fun sum(a: Int = 1, b: Int = 2) = a + b，调用时: sum(b = 2)
 *
 * 如果函数参数有默认值，可以混用位置参数与命名参数。如fun sum(a: Int = 1, b: Int = 2) = a + b，调用时: sum(1, b = 2) // 位置参数需要在前
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
 * <h2>函数式表达式</h2>
 * 如果函数体只有单个表达式，则可以省略{}和return。如fun sum(a: Int, b: Int) = a + b
 *
 * <h2>中缀表示法</h2>
 * 标有 infix 关键字的函数可以使用中缀表示法（忽略该调用的点与圆括号）调用。
 *
 * <h2>函数式编程</h2>
 * Kotlin中，函数是头等的
 * 可以存储在变量和数据结构中。
 * 可以作为参数传递给其他高阶函数以及从其他高阶函数返回。
 * 可以像操作任何其他非函数值一样操作函数。
 *
 * <h2>函数作用域</h2>
 * 顶层函数：可以在文件顶层声明函数，不需要依赖类来保存一个函数。
 * 局部函数：可以在一个函数内部声明一个函数
 * 成员函数：类中声明的函数
 *
 * <h2>作用域函数</h2>
 * @see ScopeFunc
 *
 * <h2>扩展函数</h2>
 * @see ExtendFunc
 *
 * <h2>内联函数</h2>
 * @see InlineFunc
 *
 * <h2>函数类型</h2>
 * @see FuncType
 *
 * <h2>lambda表达式</h2>
 * @see Lambda
 *
 * <h2>高阶函数</h2>
 * @see HighFunc
 *
 */
fun main() {
    val a = intArrayOf(2, 3)
    sum(1, 2, 3, *a)
}

//fun sum(a: Int = 1, b: Int = 2) = a + b
fun sum(vararg ts: Int): Int {
    var sum = 0
    for (t in ts) {
        sum += t
    }
    return sum
}
 