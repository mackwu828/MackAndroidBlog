package com.mackwu.java_kt.kt.pattern.singleton

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 16:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class LazilySingleton {

    companion object {
        private var instance: LazilySingleton? = null
            get() {
                if (null == field) {
                    field = LazilySingleton()
                }
                return field
            }

        fun get(): LazilySingleton {
            return instance!!
        }
    }
}