package com.mackwu.kt.claz

/**
 * ===================================================
 * Created by MackWu on 2019/10/17 15:27
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 类
 * 用关键字class声明类
 * "class 类名" 如 class Student
 *
 * <h2>构造函数</h2>
 * 用关键字constructor声明构造函数
 * "class 类名 constructor(参数列表)"
 *
 * 在类名后面声明的构造函数叫做主构造函数，在类中声明的构造函数叫做次构造函数
 *      class Student constructor(name: String){ // 主构造函数
 *          constructor(name: String, age: Int): this(name) // 次构造函数
 *      }
 *
 * 主构造函数默认是public的，这时constructor可以省略。如 class Student(name: String)
 * 如果主构造函数声明为private的，这时constructor不可以省略。如 class private constructor(name: String)
 * 如果类没有声明任何构造函数，默认会生成一个无参的主构造函数。如 class Student
 *
 * <h2>初始化块</h2>
 * 主构造函数不能包含任何的代码，类初始化的代码可以放到以关键字init的初始化块中
 *      class Student(name: String){
 *          init {
 *              println("xxx")
 *          }
 *      }
 *
 * <h2>创建对象</h2>
 * kotlin中没有new关键字，直接调用构造函数创建对象。如val student = Student()
 *
 */

// 在类名后面声明的构造函数叫做主构造函数，在类中声明的构造函数叫做次构造函数
class Student constructor(name: String) { // 主构造函数
    constructor(name: String, age: Int) : this("xxx") // 次构造函数
}

// 主构造函数默认是public的，这时constructor可以省略
class Student2 (name: String)

// 如果主构造函数声明为private的，这时constructor不可以省略
class Student3 private constructor(name: String)

// 如果类没有声明任何构造函数，默认会生成一个无参的主构造函数
class Student4

// 主构造函数不能包含任何的代码，类初始化的代码可以放到以关键字init的初始化块中
class Student5{
    // 初始化块
    init {
        println("xxx")
    }
}

object Student6{

}

fun main() {
    // kotlin中没有new关键字，直接调用构造函数创建对象
    val student = Student("xx")
    student.toString()
}

 