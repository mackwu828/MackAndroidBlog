package com.mackwu.java_kt.kt.pattern.singleton

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 17:18
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class DoubleCheckSingleton private constructor() {

    companion object {
        val instance: DoubleCheckSingleton by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { DoubleCheckSingleton() }
    }
}