package com.mackwu.kt.pattern.singleton

import java.util.concurrent.Executors

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 15:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * 饿汉式单例线程安全
 */
fun testEarly() {
    val executorService = Executors.newFixedThreadPool(20)
    for (i in 0..20) {
        executorService.execute {
            println(Thread.currentThread().name + ": " + EarlySingleton)
        }
    }
    executorService.shutdown()
}

/**
 * 懒汉式单例线程不安全
 */
fun testLazily() {
    val executorService = Executors.newFixedThreadPool(20)
    for (i in 0..20) {
        executorService.execute {
            println(Thread.currentThread().name + ": " + LazilySingleton.get())
//            println(Thread.currentThread().name + ": " + JavaLazilySingleton.getInstance().toString())
        }
    }
    executorService.shutdown()
}


fun main() {
    testLazily()
}