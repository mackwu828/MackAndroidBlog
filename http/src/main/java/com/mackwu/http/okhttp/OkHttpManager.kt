package com.mackwu.http.okhttp

import android.content.Context
import android.util.Log
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * ===================================================
 * Created by MackWu on 2019/11/20 15:18
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object OkHttpManager {

    private lateinit var okHttpClient: OkHttpClient

    /**
     * 初始化
     * connectTimeout: 连接超时时间
     * readTimeout: 读取超时时间
     * writeTimeout: 写入超时时间
     * retryOnConnectionFailure: 超时重试
     * hostnameVerifier: 信任所有证书
     * cookieJar: cookie持久化
     * addInterceptor: 拦截器
     */
    fun init(context: Context) {
        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier { _, _ -> true }
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))) // cookieJar
                .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }) // httpLoggingInterceptor
                .build()
    }

    private fun Call.enqueue(onResponse: ((result: String) -> Unit)? = null, onFailure: ((call: Call, e: IOException) -> Unit)? = null) {
        enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Returns true if the code is in [200..300), which means the request was successfully received, understood, and accepted.
                if (response.isSuccessful) {
                    val body = response.body()
                    if (null == body) {
                        onFailure?.invoke(call, IOException("body is null"))
                        return
                    }
                    Log.d("TAG", body.string())
                    onResponse?.invoke(body.string())
                    return
                }
                onFailure?.invoke(call, IOException("${response.code()} ${response.message()}"))
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.d("TAG",  e.message)
                onFailure?.invoke(call, e)
            }
        })
    }


    private fun Call.execute() {
//        val response = okHttpClient.newCall(request).execute()
    }


    /**
     * get
     */
    fun get(url: String, onResponse: (result: String) -> Unit) {
        val request = Request.Builder()
                .url(url)
                .build()
        okHttpClient.newCall(request).enqueue(onResponse)
    }

    fun get(url: String, paramMap: HashMap<String, String>, onResponse: (result: String) -> Unit) {
        val httpUrl = HttpUrl.parse(url)!!.newBuilder()
                .apply {
                    for ((name, value) in paramMap) {
                        addEncodedQueryParameter(name, value)
                    }
                }
                .build()
        val request = Request.Builder()
                .url(httpUrl)
                .build()
        okHttpClient.newCall(request).enqueue(onResponse)
    }
    /**
     * post json
     */
    fun postJson(url: String, json: String, onResponse: (result: String?) -> Unit) {
        val requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json)
        val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
        okHttpClient.newCall(request).enqueue(onResponse)
    }

    /**
     * post form
     */
    private fun postForm(url: String, param: HashMap<String, String>, onResponse: (result: String?) -> Unit) {
        val formBody = FormBody.Builder()
                .apply { for ((name, value) in param) add(name, value) }
                .build()
        val request = Request.Builder()
                .url(url)
                .post(formBody)
                .build()
        okHttpClient.newCall(request).enqueue(onResponse)
    }

}

fun main() {
    OkHttpManager.get("http://www.baidu.com"){
        println(it)
    }
}