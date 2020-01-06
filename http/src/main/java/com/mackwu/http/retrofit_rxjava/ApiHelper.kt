package com.mackwu.http.retrofit_rxjava

/**
 * ===================================================
 * Created by MackWu on 2019/12/11 15:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object ApiHelper {

    private val ipApi = RetrofitManager.getApi(IpApi::class.java)

    fun getIp() {
        ipApi.getIp().subscribeNext { }
    }
}