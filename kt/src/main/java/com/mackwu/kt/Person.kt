package com.mackwu.kt

/**
 * ===================================================
 * Created by MackWu on 2020/3/27 18:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class Person {

    fun set(action: IAction) {

    }
}

fun main() {
    val person = Person()
    person.set(object : IAction{
        override fun doSomething() {
        }
    })
}

