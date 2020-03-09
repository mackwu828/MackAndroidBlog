package com.mackwu.http.okhttp_retrofit_rxjava

import com.mackwu.http.bean.Ip

/**
 * ===================================================
 * Created by MackWu on 2019/12/11 15:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object ApiHelper {

    private val ipApi = RetrofitManager.getApi(IpApi::class.java)

    fun getIp(onNext: (ip: Ip) -> Unit) {
        ipApi.getIp().subscribeNext { ip -> onNext.invoke(ip) }
    }
}