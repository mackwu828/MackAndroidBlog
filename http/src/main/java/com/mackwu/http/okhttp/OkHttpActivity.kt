package com.mackwu.http.okhttp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.mackwu.http.R
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.internal.cache.CacheInterceptor
import okhttp3.internal.connection.ConnectInterceptor
import okhttp3.internal.http.BridgeInterceptor
import okhttp3.internal.http.CallServerInterceptor
import okhttp3.internal.http.RetryAndFollowUpInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * ===================================================
 * Created by MackWu on 2019/10/8 18:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * OkHttp
 * An HTTP client for Android, Kotlin, and Java
 * @link https://github.com/square/okhttp
 * @link https://square.github.io/okhttp/
 *
 * <h2>httpClient初始化</h2>
 * 方法1：使用默认的httpClient
 * @see init()
 * 方法2：自定义httpClient
 * @see initCustom()
 * 连接超时时间：connectTimeout(10, TimeUnit.SECONDS)
 * 读取超时时间：readTimeout(10, TimeUnit.SECONDS)
 * 超时重试：retryOnConnectionFailure(true)
 * 信任所有证书：hostnameVerifier { _, _ -> true }
 * cookie持久化
 * 日志拦截器
 *
 * <h2>get请求</h2>
 *
 * <h2>post请求</h2>
 * post请求1: 提交json数据
 * @see postJson
 * post请求2：提交表单数据
 * @see postForm
 *
 * <h2>源码流程</h2>
 * @see RealCall.getResponseWithInterceptorChain()
 *
 * <h2>拦截器</h2>
 * okhttp内部定义了五个拦截器
 * @see RetryAndFollowUpInterceptor
 * @see BridgeInterceptor
 * @see CacheInterceptor
 * @see ConnectInterceptor
 * @see CallServerInterceptor
 *
 * 自定义拦截器：
 * 日志拦截、cookie拦截、基本参数拦截、缓存拦截
 * @see MyInterceptor
 *
 * <h2>ConnectionPool连接池</h2>
 * @see ConnectionPoolActivity
 *
 */
class OkHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { get() }
    }

    private fun init() {
        val okHttpClient = OkHttpClient()
//        val okHttpClient = OkHttpClient.Builder().build()
    }

    private fun initCustom() {
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier { _, _ -> true }

        // cookie持久化
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(this))
        okHttpClient.cookieJar(cookieJar)

        // 日志拦截器
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addNetworkInterceptor(httpLoggingInterceptor)
    }

    private fun get() {
        val okHttpClient = OkHttpClient()
        // request
        val url = "https://raw.github.com/square/okhttp/master/README.md"
        val request = Request.Builder()
                .url(url)
                .build()
        // newCall enqueue
        okHttpClient.newCall(request)
                .enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        // Returns true if the code is in [200..300), which means the request was successfully received, understood, and accepted.
                        if (response.isSuccessful) {
                            val result = response.body()?.string()
                            Log.d("TAG", result)
                        }
                    }
                })
        // newCall execute
//        val response = okHttpClient.newCall(request).execute()
    }

    private fun postJson() {
        val okHttpClient = OkHttpClient()
        // request
        val url = ""
        val json = ""
        val requestBody = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json)
        val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
        // execute
        okHttpClient.newCall(request).execute()
    }

    private fun postForm() {
        val okHttpClient = OkHttpClient()
        // request
        val url = ""
        val requestBody = FormBody.Builder()
                .add("name", "mack")
                .add("age", "26")
                .build()
        val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()
        // execute
        okHttpClient.newCall(request).execute()
    }

}
