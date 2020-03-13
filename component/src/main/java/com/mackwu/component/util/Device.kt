package com.mackwu.component.util

import java.net.NetworkInterface
import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2019/10/28 16:51
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * 获取mac地址
 * @param type eth0 wlan0
 */
private fun getMac(type: String): String {
    try {
        val networkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (networkInterface in networkInterfaces) {
            if (networkInterface.name.toLowerCase(Locale.getDefault()) == type) {
                val byteArray = networkInterface.hardwareAddress ?: return ""
                val stringBuilder = StringBuilder()
                for (b in byteArray) {
                    stringBuilder.append(String.format("%02X:", b))
                }
                if (stringBuilder.isNotEmpty()) {
                    stringBuilder.deleteCharAt(stringBuilder.length - 1)
                }
                return stringBuilder.toString()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

val mac: String
    get() = if (getMac("eth0").isNotEmpty()) getMac("eth0") else getMac("wlan0")


val euiMac: String
    get() {

        return ""
    }
