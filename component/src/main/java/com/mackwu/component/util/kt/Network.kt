package com.mackwu.component.util.kt

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager


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
private val Context.wifiManager
    get() = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
private val Context.wifiInfo
    get() = wifiManager.connectionInfo

/**
 * 检查是否有网络
 */
val Context.isNetworkAvailable: Boolean
    get() = networkInfo?.isAvailable ?: false

/**
 * 检查是否是WIFI
 */
val Context.isWifi: Boolean
    get() = networkInfo?.type == ConnectivityManager.TYPE_WIFI

/**
 * 检查是否是移动网络
 */
val Context.isMobile: Boolean
    get() = networkInfo?.type == ConnectivityManager.TYPE_MOBILE


/**
 * ip地址
 */
val Context.ip: String
    get() {
        val ipInt = wifiInfo.ipAddress
        val sb = StringBuilder()
        sb.append(ipInt and 0xFF).append(".")
        sb.append(ipInt shr 8 and 0xFF).append(".")
        sb.append(ipInt shr 16 and 0xFF).append(".")
        sb.append(ipInt shr 24 and 0xFF)
        return sb.toString()
    }

//val ip: String
//    get() {
//        val en = NetworkInterface.getNetworkInterfaces()
//        while (en.hasMoreElements()) {
//            val addresses = en.nextElement().inetAddresses
//            while (addresses.hasMoreElements()) {
//                val address = addresses.nextElement()
//                if (!address.isLoopbackAddress && address is Inet4Address) {
//                    return address.hostAddress.toString()
//                }
//            }
//        }
//        return ""
//    }