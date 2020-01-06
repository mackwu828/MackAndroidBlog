package com.mackwu.http.retrofit_coroutines

import com.mackwu.http.bean.Ip
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * ===================================================
 * Created by MackWu on 2019/12/11 15:35
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface IpApi {

    /**
     * 获取ip
     */
    @GET
    suspend fun getIp(@Url url: String = "http://ip-api.com/json"): Ip
}