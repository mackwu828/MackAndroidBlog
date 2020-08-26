package com.mackwu.kt.pattern.builder

/**
 * ===================================================
 * Created by MackWu on 2020/3/23 15:04
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class Person2(
        val name: String,
        val age: Int
) {

    constructor(builder: Builder) : this(builder.name, builder.age)

    class Builder {
        var name: String = ""
        var age: Int = 0

        fun build(): Person2 {
            return Person2(this)
        }
    }

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun main() {
    val person = Person2.build {
        name = "aaa"
        age = 11
    }
}