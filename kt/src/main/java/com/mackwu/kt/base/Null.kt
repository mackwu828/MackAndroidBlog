package com.mackwu.kt.base

import com.mackwu.kt.bean.Person


/**
 * ===================================================
 * Created by MackWu on 2019/10/22 17:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 空安全
 * 为了避免空指针异常，所有的变量默认都是非空的，如果要定义一个可空的对象，要显示的声明为可空类型。
 * ? 表示变量可以为空，默认不能为空。如 val person: Person? = null
 *
 * <h2>安全调用操作符?.</h2>
 * 表示非空时调用，否则返回null。如 person?.name
 *
 * <h2>Elvis操作符?:</h2>
 * 表示非空时使用前面的，否则使用后面的。可以用来替代对象可空时的if语句。如 person ?: Person("", 22)
 *
 * <h2>非空断言运算符!!</h2>
 * 表示变量不能为空。相当于Java的断言，如果变量为空，会抛出KotlinNullPointException。
 * 如 person!!
 * person!!.name
 */
class Null

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
