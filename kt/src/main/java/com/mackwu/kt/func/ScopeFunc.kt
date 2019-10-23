package com.mackwu.kt.func

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.mackwu.kt.bean.Person

/**
 * ===================================================
 * Created by MackWu on 2019/10/21 16:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 作用域函数
 * kotlin标准库中提供了一些函数，用来在临时的作用域内执行代码。在这些作用域内，无需通过对象名就能访问对象。
 * 作用域函数有：run、with、T.run、T.let、T.also、T.apply
 *
 * <h2>run</h2>
 * 没啥作用，有返回值
 * @see runTest
 *
 * <h2>with</h2>
 * with 括号中传入对象，用 this 操作对象，this 可以省略
 * @see withTest
 *
 * <h2>T.run</h2>
 * T.run 用 this 操作对象，this可以省略，和 with 作用一样
 * @see tRunTest
 *
 * <h2>T.let</h2>
 * T.let 用 it 操作对象
 * @see tLetTest
 *
 * <h2>T.also</h2>
 * T.also 用 it 操作对象，和 T.let 作用一样
 * @see tAlsoTest
 *
 * <h2>T.apply</h2>
 * T.apply 把 this 作为参数，并返回了 this，很容易实现链式调用
 * @see tApplyTest
 *
 *
 * <h2>总结</h2>
 * T.run 可以替代 run 和 with，如果不能省略 this 时使用 T.let，想要返回值用 T.also
 * T.apply 用来链式调用
 */
class ScopeFunc

private fun runTest() {
    run {
        println("my name is wmj")
    }
    val str = run {
        val name = "mack"
        "my name is $name"
    }
}

private val person = Person("mack", 25)
val person2: Person? = Person("mack2", 25)
private fun withTest() {
    with(person) {
        name = ""
        age = 30
    }

    // 如果对象是可空的，this不可以省略
    with(person2) {
        this?.name = ""
        this?.age = 30
    }
}


private fun tRunTest() {
    // T.run 用 this 操作对象，this可以省略，和 with 作用一样
    person.run {
        name = ""
        age = 30
    }

    // 但是如果对象是可空的，用 T.run 比 with 更简洁
    person2?.run {
        name = ""
        age = 30
    }
}

private fun tLetTest() {
    // T.let用it操作对象
    person.let {
        it.name = "mack"
        it.age = 30
    }

    // 如果对象是可空的
    person2?.let {
        it.name = "mack"
        it.age = 30
    }

    // T.run 比 T.let 在使用上更加简洁，但是 T.let 有以下优势
    // 1. 更清楚的区分变量与外部类的成员
    // 2. this 不能省略的情况下，it 比 this 更短更清晰
    // 3. it 可以更换成其他名字
    person2?.let { student ->
        student.name = "mack"
        student.age = 22
    }
}

private fun tAlsoTest() {
    // T.also 用 it 操作对象，和  T.let作用一样
    person.let {
        it.name = "mack"
        it.age = 30
    }

    // 但是他们的返回值不同，T.let 返回最后一个对象的值，T.also 返回 this
    val a: Int? = person2?.let {
        it.name = ""
        it.age
    }
    val b: Person? = person2?.also {
        it.name = ""
        it.age = 30
    }
}


private fun tApplyTest(context: Context){
    // T.apply 把 this 作为参数，并返回了 this，很容易实现链式调用
    val starter = Intent(context, Activity::class.java)
            .apply { putExtra("key", 1) }
            .apply { putExtra("key2", 2) }
}
