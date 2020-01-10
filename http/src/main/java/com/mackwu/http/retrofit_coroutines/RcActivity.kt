package com.mackwu.http.retrofit_coroutines

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 16:56
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 如果使用GlobalScope启动的协程越多，就必须定义越多的变量持有对启动协程的引用，并在onDestroy的时候cancel掉所有协程
 */
class RcActivity : AppCompatActivity() {

    private var job1: Job? = null
    private var job2: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job1 = GlobalScope.launch { RetrofitManager.getApi(IpApi::class.java).getIp() }
        job2 = GlobalScope.launch { RetrofitManager.getApi(IpApi::class.java).getIp() }
    }

    override fun onDestroy() {
        super.onDestroy()
        job1?.cancel()
        job2?.cancel()
    }
}


class RcActivity10 : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch { val job1 = RetrofitManager.getApi(IpApi::class.java).getIp() }
        launch { RetrofitManager.getApi(IpApi::class.java).getIp() }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}


/**
 * 用MainScope代替GlobalScope的使用
 * 只需要调用mainScope.cancel，就会cancel掉所有使用mainScope启动的所有协程
 */
class RcActivity2 : AppCompatActivity() {
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainScope.launch { RetrofitManager.getApi(IpApi::class.java).getIp() }
        mainScope.launch { RetrofitManager.getApi(IpApi::class.java).getIp() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}




/**
 * 实现CoroutineScope
 * kotlin委托模式
 */
class RcActivity11 : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch { RetrofitManager.getApi(IpApi::class.java).getIp() }
        launch { RetrofitManager.getApi(IpApi::class.java).getIp() }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}

/**
 * Lifecycle
 */
class RcActivity20 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}