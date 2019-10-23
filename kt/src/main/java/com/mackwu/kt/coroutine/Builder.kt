package com.mackwu.kt.coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * ===================================================
 * Created by MackWu on 2019/10/16 15:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 协程构建器
 * 用来创建协程。常见的协程构建器有launch、runBlocking、withContext、async
 *
 * <h2>launch<h2>
 * 通过[launch]创建协程，不阻塞当前线程。需要阻塞当前线程后，才会执行协程里的内容
 * see [launchTest]
 *
 * public fun CoroutineScope.launch(
 * context: CoroutineContext = EmptyCoroutineContext,
 * start: CoroutineStart = CoroutineStart.DEFAULT,
 * block: suspend CoroutineScope.() -> Unit
 * ): Job
 * CoroutineContext: 协程上下文
 * CoroutineDispatcher: 协程调度器
 * Job: 任务，封装了协程中需要执行的代码逻辑。
 * Job可以取消并且有简单生命周期。[cancel]
 * 它有三种状态：[isActive] [isCompleted] [isCancelled]
 * see [launchTest2]
 *
 *
 *
 * <h2>runBlocking</h2>
 * 通过[runBlocking]创建协程，同时阻塞当前线程。这个不应该在协程中使用，主要是为main函数和测试设计的
 * see [runBlockingTest]
 *
 * <h2>withContext</h2>
 * [withContext]不会创建新的协程，在指定协程上运行挂起代码块，并挂起该协程直至代码块运行完成
 *
 * <h2>async</h2>
 * [async]功能和launch一样，区别是有返回值。他的返回值需要通过await函数获取，他也是个挂起函数，
 * 调用时会挂起当前协程直到async中代码执行完并返回某个值
 * see [asyncTest1]
 */

fun main() {
}

/**
 * launch
 */
private fun launchTest(){
    GlobalScope.launch {
        // 协程挂起1s
        delay(1000)
        println("World!")
    }
    println("Hello,")
    // 主线程阻塞2s
    Thread.sleep(2000)
}

private fun launchTest2() = runBlocking{
    val job = launch(Dispatchers.Default) {
        delay(3000)
        println("World!")
    }
    job.isCancelled
    println("Hello,")
    delay(2000)
    job.cancel()
}


/**
 * runBlocking
 */
private fun runBlockingTest() = runBlocking{
    GlobalScope.launch {
        delay(1000)
        println("World!")
    }
    println("Hello,")
    delay(2000)
    // 立即输出Hello, 主协程挂起2s。launch的协程挂起1s后输出World!
    //    Hello,
    //    World!
}


/**
 * withContext
 */
private fun withContextTest(){
}


/**
 * async
 */
private fun asyncTest1() = runBlocking {
    val time = measureTimeMillis {
        val one = async { doSth1() }
        val two = async { doSth2() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
    // 两个协程并发执行，速度快了两倍
//    The answer is 42
//    Completed in 1033 ms
}

private suspend fun doSth1(): Int {
    delay(1000)
    return 13
}

private suspend fun doSth2(): Int {
    delay(1000)
    return 29
}

private fun asyncTest2() = runBlocking {
    val time = measureTimeMillis {
        // 延迟
        val one = async(start = CoroutineStart.LAZY) { doSth1() }
        val two = async(start = CoroutineStart.LAZY) { doSth2() }
        one.start()
        two.start()
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}