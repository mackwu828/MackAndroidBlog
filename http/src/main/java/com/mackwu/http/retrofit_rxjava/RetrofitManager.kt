package com.mackwu.http.retrofit_rxjava

import android.content.Context
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * ===================================================
 * Created by MackWu on 2018/7/9 17:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
object RetrofitManager {

    // 超时时间
    private const val DEFAULT_TIMEOUT = 15L
    // retrofit
    private lateinit var retrofit: Retrofit
    // serviceMap
    private val serviceMap = hashMapOf<String, Any>()

    fun init(context: Context){
        init(context, true, "")
    }

    fun init(context: Context, logDebug: Boolean, baseUrl: String) {
        // okHttpClient
        val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .hostnameVerifier { _, _ -> true }
                .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))) // cookieJar
                .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = if (logDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE })
                .build()


        // Retrofit
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }


    @Suppress("UNCHECKED_CAST")
    fun <S> getApi(serviceClass: Class<S>): S {
        // 保存着上一个 base url 的 serviceClass ？
        return if (serviceMap.containsKey(serviceClass.name)) {
            serviceMap[serviceClass.name] as S
        } else {
            val obj: S = retrofit.create(serviceClass)
            serviceMap[serviceClass.name] = retrofit.create(serviceClass) as Any
            obj
        }
//        return retrofit.create(serviceClass)
    }
}