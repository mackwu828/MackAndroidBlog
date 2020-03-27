package com.mackwu.http.okhttp.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * ===================================================
 * Created by MackWu on 2019/10/30 14:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * BaseUrl拦截器
 * 在接口添加请求头，根据请求头切换BaseUrl。
 * 默认是DP_URL。如果在接口中添加@Headers("Domain-Name: accu")，BaseUrl会切换成ACCU_URL。
 */
@Deprecated("有bug")
open class BaseUrlInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
//        logD("BaseUrlInterceptor intercept... ${request.url()}")
        val headerValues = request.headers("Domain-Name")
        if (headerValues.isNotEmpty()) {
//            val baseUrl = HttpUrl.parse(when (headerValues[0]) {
//                "ip" -> ""
//                "accu" -> ""
//            }) as HttpUrl
//            logD("header: ${headerValues[0]}, baseUrl: ${baseUrl.url()}")

            // create new httpUrl
            val httpUrl = request.url().newBuilder()
            for (i in 0 until request.url().pathSize()) {
                // 当删除了上一个 index, PathSegment 的 item 会自动前进一位, 所以 remove(0) 就好
                httpUrl.removePathSegment(0)
            }
//            httpUrl.scheme(baseUrl.scheme())
//                    .host(baseUrl.host())
            for (pathSegment in request.url().pathSegments()) {
                httpUrl.addEncodedPathSegments(pathSegment)
            }
//            logD("new httpUrl: ${httpUrl.build().url()}")


            // create new request
            val newRequest = request.newBuilder()
                    .removeHeader("Domain-Name")
                    .url(httpUrl.build())
                    .build()
            return chain.proceed(newRequest)
        }
        return chain.proceed(request)
    }

}