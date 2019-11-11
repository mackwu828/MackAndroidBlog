package com.mackwu.kt.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * ===================================================
 * Created by MackWu on 2019/7/26 15:44
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
private val Context.connectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
private val Context.networkInfo: NetworkInfo?
    get() = connectivityManager.activeNetworkInfo

/**
 * 检查是否有网络
 */
val Context.isNetworkAvailable: Boolean
    get() = null != networkInfo && networkInfo!!.isAvailable

/**
 * 检查是否是WIFI
 */
val Context.isWifi: Boolean
    get() = null != networkInfo && networkInfo!!.type == ConnectivityManager.TYPE_WIFI

/**
 * 检查是否是移动网络
 */
val Context.isMobile: Boolean
    get() = null != networkInfo && networkInfo!!.type == ConnectivityManager.TYPE_MOBILE


/**
 * 监听是否有网络
 */
//fun Context.requestNetworkAvailable(listener: () -> Unit){
//    connectivityManager.requestNetwork(NetworkRequest.Builder().build(), object : ConnectivityManager.NetworkCallback(){
//        override fun onAvailable(network: Network?) {
//            logD("requestNetworkAvailable: onAvailable")
//            listener.invoke()
//        }
//    })
//}