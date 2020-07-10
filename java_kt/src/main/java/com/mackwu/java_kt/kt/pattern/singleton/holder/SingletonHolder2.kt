package com.mackwu.java_kt.kt.pattern.singleton.holder

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 17:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
open class SingletonHolder2<out T, in A1, in A2>(private val constructor: (A1, A2) -> T) {

    @Volatile
    private var instance: T? = null

    fun getInstance(arg1: A1, arg2: A2): T {
        return when {
            instance != null -> instance!!
            else -> synchronized(this) {
                if (instance == null) instance = constructor(arg1, arg2)
                instance!!
            }
        }
    }
}