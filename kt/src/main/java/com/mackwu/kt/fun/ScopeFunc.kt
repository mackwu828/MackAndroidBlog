package com.mackwu.kt.`fun`

import android.content.Context
import com.mackwu.kt.claz.Person

/**
 * ===================================================
 * Created by MackWu on 2019/10/21 16:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 作用域函数
 *
 * 总结：
 * T.run 可以替代 run 和 with，如果不能省略 this 时使用 T.let，想要返回值用 T.also
 * T.apply 用来链式调用
 */

/**
 * 作用域函数。kotlin标准库中提供了一些函数，用来在临时的作用域内执行代码。在这些作用域内，无需通过对象名就能访问对象。
 * run没啥作用，有返回值
 */
fun run1() {
    run {
        println("my name is wmj")
    }
}

fun run2() {
    val name = run {
        val name = "mack"
        "my name is $name"
    }
}

/**
 * with括号中传入对象，用 this 操作对象，this 可以省略
 */
fun with1() {
    val person = Person("mack", 25)
    with(person) {
        name = ""
        age = 30
    }
}

/**
 * with 如果对象是可空的，this不可以省略
 */
fun with2() {
    val person: Person? = Person("mack", 25)
    with(person) {
        this?.name = ""
        this?.age = 30
    }
}


/**
 * T.run 用 this 操作对象，this可以省略
 */
fun tRun1() {
    val person = Person("mack", 25)
    person.run {
        name = ""
        age = 30
    }
}

/**
 * T.run 如果对象可空
 */
fun tRun2() {
    val person: Person? = Person("mack", 25)
    person?.run {
        name = ""
        age = 30
    }
}


/**
 * T.let 用 it 操作对象
 */
fun tLet1() {
    val person = Person("mack", 25)
    person.let {
        it.name = "mack"
        it.age = 30
    }
}

/**
 * T.let 如果对象是可空的
 */
fun tLet2() {
    val person: Person? = Person("mack", 25)
    person?.let {
        it.name = ""
        it.age = 30
    }
}

/**
 * T.let 有返回值，返回最后一行的值
 */
fun tLet3() {
    val person: Person = Person("mack", 25)
    val age: Int = person.let {
        it.name = ""
        it.age = 30
        it.age
    }
}

/**
 *  T.let it可以更换成其他名称
 */
fun tLet4() {
    val person: Person? = Person("mack", 25)
    person?.let { student ->
        student.name = "mack"
        student.age = 22
    }
}


/**
 * T.also 用 it 操作对象
 */
fun tAlso1() {
    val person = Person("mack", 25)
    person.also {
        it.name = "mack"
        it.age = 30
    }
}

/**
 * T.also 有返回值，返回this
 */
fun tAlso2() {
    val person = Person("mack", 25)
    val student = person.also {
        it.name = "mack"
        it.age = 30
    }
}


/**
 * T.apply 用 this 操作对象
 */
fun tApply1(context: Context) {
    val person = Person("mack", 25)
    person.apply { name = "xxx" }
}

/**
 * T.apply 有返回值，返回this
 */
fun tApply2(context: Context) {
    val person = Person("mack", 25)
    val mack = person.apply { name = "xxx" }
            .apply { age = 30 }
}