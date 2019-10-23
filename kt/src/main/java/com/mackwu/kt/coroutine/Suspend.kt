package com.mackwu.kt.coroutine

import kotlinx.coroutines.*

/**
 * ===================================================
 * Created by MackWu on 2019/10/16 15:18
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 挂起函数
 * 不会阻塞线程，但是会挂起协程。挂起函数执行完成后，才会执行后面的代码。
 * 系统的挂起函数有：delay
 *
 * <h2>delay</h2>
 * [delay]
 * [delayTest1]
 *
 * 如果挂起的时间超过主线程阻塞的时间，则不会执行协程里的内容
 * [delayTest2]
 *
 *
 * <h2>suspend自定义挂起函数</h2>
 * 用[suspend]修饰函数表示挂起函数
 * [suspendTest]
 */

private fun delayTest1(){
    GlobalScope.launch {
        // 协程挂起1s
        delay(1000)
        println("World!")
    }
    println("Hello,")
    Thread.sleep(2000)
    // 立即输出Hello, 主线程阻塞2s，协程执行，协程被挂起1s后输出World!
    //    Hello,
    //    World!
}


private fun delayTest2(){
    GlobalScope.launch {
        // 协程挂起3s
        delay(3000)
        println("World!")
    }
    println("Hello,")
    Thread.sleep(2000)
    // 立即输出Hello, 主线程阻塞2s，协程执行，协程被挂起3s，2s后主线程阻塞结束，无输出。
    // 协程仍被挂起？可以取消协程
    //    Hello,
}

private suspend fun doWorld(){
    delay(100)
    println("World!")
}

private fun suspendTest() = runBlocking{
    doWorld()
    println("Hello,")
    delay(2000)
}