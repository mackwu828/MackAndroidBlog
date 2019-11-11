package com.mackwu.kt

/**
 * ===================================================
 * Created by MackWu on 2019/10/17 15:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 用关键字constructor声明构造函数
 * 构造函数默认是public的，这时constructor可以省略
 * 如果构造函数声明为private的，这时constructor不可以省略
 * 如果没有显示声明任何构造函数，默认会生成一个无参的构造函数
 */
class Person2 constructor(name: String)
class Person3(name: String) // 省略constructor
class Person4 private constructor(name: String) // private时不能省略constructor
class Person5

/**
 * 主构造函数不能包含任何的代码，类初始化的代码可以放到以关键字init的初始化块中
 */
class Person10 {
    // 初始化块
    init {
        println("xxx")
    }
}

/**
 * 在类名后面声明的构造函数叫做主构造函数，在类中声明的构造函数叫做次构造函数
 */
class Person20 constructor(name: String) { // 主构造函数
    constructor(name: String, age: Int) : this("xxx") // 次构造函数
}



//enum class Direction {
//    NORTH,
//    SOUTH
//}
//enum class Direction(val value: Int) {
//    NORTH(0),
//    SOUTH(1),
//}
