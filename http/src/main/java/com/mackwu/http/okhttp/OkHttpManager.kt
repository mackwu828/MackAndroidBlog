package com.mackwu.http.okhttp

import android.content.Context
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

    fun init(context: Context) {
        // httpLoggingInterceptor
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        // cookieJar
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))

        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier { _, _ -> true }
                .cookieJar(cookieJar)
                .addInterceptor(httpLoggingInterceptor)
                .build()
    }

    private fun Call.enqueue(onResponse: ((result: String?) -> Unit)? = null, onFailure: ((call: Call, e: IOException) -> Unit)? = null) {
        enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Returns true if the code is in [200..300), which means the request was successfully received, understood, and accepted.
                if (response.isSuccessful) {
                    val result = response.body()?.string()
                    onResponse?.invoke(result)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
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
    fun get(url: String, onResponse: (result: String?) -> Unit) {
        val request = Request.Builder()
                .url(url)
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