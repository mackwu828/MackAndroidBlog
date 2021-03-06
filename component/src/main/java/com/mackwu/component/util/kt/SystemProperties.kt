package com.mackwu.component.util.kt

import android.annotation.SuppressLint
import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 10:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * 获取系统属性
 * @param key 系统属性键
 * @param defaultValue 默认值
 */
@SuppressLint("PrivateApi")
fun getSystemProp(key: String, defaultValue: String): String {
    try {
        val systemProperties = Class.forName("android.os.SystemProperties")
        val getProp = systemProperties.getDeclaredMethod("get", String::class.java, String::class.java)
        return getProp.invoke(systemProperties, key, defaultValue) as String
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}

/**
 * 设置系统属性
 * @param key 系统属性键
 * @param value 系统属性值
 */
@SuppressLint("PrivateApi", "DiscouragedPrivateApi")
fun setSystemProp(key: String, value: String) {
    try {
        val systemProperties = Class.forName("android.os.SystemProperties")
        val setProp = systemProperties.getDeclaredMethod("set", String::class.java, String::class.java)
        setProp.invoke(systemProperties, key, value)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun getLocalProp(key: String, defaultValue: String): String {
    return Properties().getProperty(key, defaultValue)
}
