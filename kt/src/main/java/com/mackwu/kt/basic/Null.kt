package com.mackwu.kt.basic

import com.mackwu.kt.claz.Person


/**
 * ===================================================
 * Created by MackWu on 2019/10/22 17:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 可空性
 * Kotlin中，所有的变量默认都是非空的，如果要定义一个可空的对象，要显示的声明为可空类型。
 *
 * 在变量类型后加？表示变量可空
 * "变量类型?" 如 val person: Person? = null
 *
 * 如果一个变量是可空的，要用安全调用操作符或者非空断言操作符去调用
 * Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type Person?
 *
 * <h2>安全调用操作符?.</h2>
 * 表示非空时调用，否则返回null。如 person?.name
 *
 * <h2>Elvis操作符?:</h2>
 * 表示非空时调用前面的语句，否则调用后面的语句。可以用来替代对象可空时的if判断。如 person ?: Person("", 22)
 *
 * <h2>非空断言操作符!!</h2>
 * 表示变量不能为空。如果变量为空，会抛出KotlinNullPointException。如 person!! 或 person!!.name
 */
fun main() {
    // ? 表示变量可以为空，默认不能为空
    val person: Person? = null

    // 可空的对象要用安全调用操作符?.调用，表示非空时调用，否则返回null
    person?.name

    // ?: 如果person不为空，使用person，否则使用Person("", 22)
    // 相当于 if (null != person) person else Person("", 22)
    val xxx = person ?: Person("", 22)


    // !! 表示变量不能为空
//    person!!

    // !!. 相当于if not null else throw new KotlinNullPointException()
    // if(null != person) person.name else throw new KotlinNullPointException()
//    person!!.name
}
