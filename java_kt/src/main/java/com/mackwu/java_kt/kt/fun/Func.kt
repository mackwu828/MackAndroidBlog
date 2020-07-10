package com.mackwu.java_kt.kt.`fun`

/**
 * ===================================================
 * Created by MackWu on 2019/10/15 18:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 函数
 */

/**
 * 用关键字fun声明函数。"fun 函数名称(参数名称: 参数类型): 返回值类型 { 函数体 }"
 */
fun sum1(a: Int, b: Int): Int {
    return a + b
}

/**
 * 函数式表达式。如果函数体只有单个表达式，可以省略{}和return
 */
fun sum2(a: Int, b: Int) = a + b




/**
 * 默认参数
 */
fun sum10(a: Int = 1, b: Int = 2) = a + b

/**
 * 如果覆盖的函数有默认值，覆盖后的函数的默认值和父类的函数的默认值相同
 */
open class Sum1 {
    open fun foo(i: Int = 10) {

    }
}

class Sum2 : Sum1() {
    override fun foo(i: Int) {

    }
}


/**
 * 命名参数。可以在调用函数时使用函数的参数名称，使代码更具有可读性
 */
fun sum20() {
    sum10(a = 1, b = 2)
}

/**
 * 如果函数参数有默认值，使用命名参数时不需要命名所有的参数
 */
fun sum21() {
    sum10(b = 2)
}

/**
 * 如果函数参数有默认值，使用命名参数时可以只使用其中的一部分，但是要写在后面
 */
fun sum22() {
    sum10(1, b = 2)
}




/**
 * 可变参数。用关键字vararg声明可变参数，可变参数是数组
 */
fun sum30(vararg ts: Int) {
    for (t in ts) {
        println(t)
    }
}

/**
 * 调用可变参数的函数时，如果想传入一个数组，可以通过伸展操作符*传入
 */
fun sum31() {
    val a = intArrayOf(2, 3)
    sum30(1, 2, 3, *a)
}


/**
 * 如果一个函数不返回任何有用的值，它的返回类型是Unit，可以省略。
 */
fun sum40(): Unit {
    // `return Unit` 或者 `return` 是可选的
//    return
    return Unit
}

/**
 * 中缀表示法。标有 infix 关键字的函数可以使用中缀表示法（忽略该调用的点与圆括号）调用。
 */
fun sum41(){

}





 