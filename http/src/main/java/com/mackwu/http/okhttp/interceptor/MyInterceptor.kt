package com.mackwu.http.okhttp.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * ===================================================
 * Created by MackWu on 2019/10/8 19:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class MyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        when (requestMethod) {
//            "GET" -> {
//                val urlBuilder = request.url().newBuilder()
//                val httpUrl = urlBuilder.build()
//                val params = httpUrl.queryParameterNames()
//                for (key in params) {
//                    Log.d("TAG", key + "=" + )
//                    paramStr = "$key=${httpUrl.queryParameter(key)}"
//                }
//            }
//            "POST" -> {
//                if (request.body() is FormBody) {
//                    val formBody = request.body() as FormBody
//                    for (i in 0 until formBody.size()) {
//                        param.append(formBody.encodedName(i)).append("=").append(formBody.encodedValue(i)).append("&")
//                    }
//                }
//            }
        return chain.proceed(chain.request())
    }
}