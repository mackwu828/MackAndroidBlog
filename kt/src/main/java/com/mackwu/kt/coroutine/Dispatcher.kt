package com.mackwu.kt.coroutine

import kotlinx.coroutines.*

/**
 * ===================================================
 * Created by MackWu on 2019/10/16 10:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 协程调度器
 * 决定协程所在的线程或线程池。
 * 它可以指定协程运行于特定的一个线程、一个线程池或者不指定任何线程（这样协程就会运行于当前线程）
 *
 *
 */

private fun dispatcherTest() = runBlocking {
    // 默认Dispatchers.Default。其指定的线程为共有的线程池
    launch (Dispatchers.Default){  }
    // 不指定线程
    launch (Dispatchers.Unconfined){  }
    // 主线程
    launch (Dispatchers.Main){  }
    // IO线程
    launch (Dispatchers.IO){  }
}