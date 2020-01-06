package com.mackwu.http.retrofit_coroutines

import kotlinx.coroutines.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/11 15:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object ApiHelper {

    private val ipApi = RetrofitManager.getApi(IpApi::class.java)
    private val mainScope = MainScope()

    fun test1() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                ipApi.getIp()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    /**
     * 嵌套请求，协程中只需顺序执行
     */
    fun test2() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val ip1 = ipApi.getIp()
                val ip2 = ipApi.getIp()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 合并请求。协程中使用async
     */
    fun test3() {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val ip1 = async { ipApi.getIp() }
                val ip2 = async { ipApi.getIp() }
                ip1.await()
                ip2.await()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    /**
     * onDestroy是调用mainScope.cancel()
     */
    fun test10() {
        mainScope.launch {
            try {
                ipApi.getIp()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}