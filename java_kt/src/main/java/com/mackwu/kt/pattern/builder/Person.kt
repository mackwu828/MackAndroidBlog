package com.mackwu.kt.pattern.builder

/**
 * ===================================================
 * Created by MackWu on 2020/3/23 15:04
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class Person(
        val name: String,
        val age: Int
) {

    constructor(builder: Builder) : this(builder.name, builder.age)

    class Builder {
        var name: String = ""
        var age: Int = 0

        fun name(name: String) = apply { this.name = name }

        fun age(age: Int) = apply { this.age = age }

        fun build(): Person {
            return Person(this)
        }
    }
}

fun main() {
    val person = Person.Builder()
            .name("111")
            .age(12)
            .build()
    person.name
}