package com.mackwu.kt.pattern.singleton

object Base {

//    private var instance: T? = null
//
//    fun getInstance(): T {
//        if (instance == null) {
//            instance = Base()
//        }
//        return instance!!
//    }

    var provider: (() -> BasicClass)? = null
        set(value) {
            if (field == null) {
                field = value
            } else {
                throw IllegalStateException("You can only register one provider for Singleton")
            }
        }

    val value: BasicClass by lazy {
        provider?.invoke()
                ?: throw IllegalStateException("You need to declare your instance provider")
    }

    inline fun <reified T : BasicClass> get(): T {
        return value as? T
                ?: throw NullPointerException("Instance is of wrong type ${value.javaClass.name}")
    }

}

abstract class BasicClass

class CustomClass private constructor() : BasicClass() {
    companion object {
        val instanceProvider: () -> BasicClass = { CustomClass() }
    }
}

fun main() {
}