package com.mackwu.kt.pattern.singleton.holder

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 17:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
open class SingletonHolder3<out T, in A1, in A2, in A3>(private val constructor: (A1, A2, A3) -> T) {

    @Volatile
    private var instance: T? = null

    fun getInstance(arg1: A1, arg2: A2, arg3: A3): T {
        return when {
            instance != null -> instance!!
            else -> synchronized(this) {
                if (instance == null) instance = constructor(arg1, arg2, arg3)
                instance!!
            }
        }
    }
}