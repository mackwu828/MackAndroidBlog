package com.mackwu.kt.coroutine

import kotlinx.coroutines.*

/**
 * ===================================================
 * Created by MackWu on 2019/10/16 15:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

fun main() {
    test12()
}

/**
 * launch：协程构建器，用来创建并启动协程。
 * GlobalScope：协程上下文。意味着新协程的生命周期只受整个应用程序的生命周期限制
 */
private fun test1(){
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000) // 非阻塞的等待 1 秒（默认时间单位是毫秒）
        println("World!") // 在延迟后打印输出
    }
    println("Hello,") // 协程已在等待时主线程还在继续
    Thread.sleep(2000) // 阻塞主线程 2 秒来保证 JVM 存活
}

/**
 * runBlocking：协程构建器，用来创建并启动协程，通常用来调试main函数或单元测试。
 */
private fun test2() = runBlocking{ // 开始执行主协程
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000)
        println("World!")
    }
    println("Hello,") // 主协程在这里会立即执行
    delay(2000) // 延迟 2 秒来保证 JVM 存活
}

/**
 * 等待一个作业
 * 显式（以非阻塞方式）等待所启动的后台 Job 执行结束
 */
private fun test10() = runBlocking {
    val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // 等待直到子协程执行结束
}

/**
 * 结构化并发
 * GlobalScope.launch是顶层协程，虽然很轻量，但它运行时仍会消耗一些内存资源。
 * 我们可以在执行操作所在的指定作用域内启动协程， 而不是像通常使用线程（线程总是全局的）那样在 GlobalScope 中启动。
 * 我们可以在这个作用域中启动协程而无需显式 join 之，外部协程（示例中的 runBlocking）直到在其作用域中启动的所有协程都执行完毕后才会结束
 */
private fun test11() = runBlocking {
    launch {  // 在 runBlocking 作用域中启动一个新协程
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}


/**
 * 作用域构建器
 * coroutineScope: 它会创建一个协程作用域并且在所有已启动子协程执行完毕之前不会结束
 */
fun test12() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }
    coroutineScope { // 创建一个协程作用域
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }
    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}


/**
 * 提取函数重构
 */
fun test20() = runBlocking{
    launch { doWorld() }
    println("Hello,")
}

/**
 * 这是你的第一个挂起函数
 * suspend: 声明为挂起函数
 */
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}