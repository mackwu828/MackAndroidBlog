package com.mackwu.java_kt.kt.coroutine

import kotlinx.coroutines.*

/**
 * ===================================================
 * Created by MackWu on 2019/10/16 10:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
fun main() {
    test1()
}

/**
 * 协程调度器
 * 协程调度器可以将协程限制在一个特定的线程执行、或将它分派到一个线程池，亦或是让它不受限地运行。
 */
private fun test1() = runBlocking {
    // 运行在父协程的上下文中，即 runBlocking 主协程
    launch { println("main runBlocking : I'm working in thread ${Thread.currentThread().name}") }
    // 将会获取默认调度器。
    launch(Dispatchers.Default) { println("Default : I'm working in thread ${Thread.currentThread().name}") }
    // 不受限的运行在主线程中。
    launch(Dispatchers.Unconfined) { println("Unconfined : I'm working in thread ${Thread.currentThread().name}") }
    // 将使它获得一个新的线程
    launch(newSingleThreadContext("MyOwnThread")) { println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}") }

//    Unconfined : I'm working in thread main
//    Default : I'm working in thread DefaultDispatcher-worker-1
//    newSingleThreadContext: I'm working in thread MyOwnThread
//    main runBlocking : I'm working in thread main

    // 限制在主线程
//    launch(Dispatchers.Main) { }
    // 限制在IO线程
//    launch(Dispatchers.IO) { }
}