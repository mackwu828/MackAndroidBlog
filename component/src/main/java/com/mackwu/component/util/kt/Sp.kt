package com.mackwu.component.util.kt

import android.content.Context
import android.preference.PreferenceManager

/**
 * ===================================================
 * Created by MackWu on 2019/9/25 14:21
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
private val Context.spName
    get() = packageName + "_preferences"

private val Context.sp
    get() = PreferenceManager.getDefaultSharedPreferences(this)

/**
 * 保存数据
 */
fun Context.put(key: String, obj: Any) {
    val editor = sp.edit()
    when (obj) {
        is Boolean -> editor.putBoolean(key, obj)
        is Float -> editor.putFloat(key, obj)
        is Int -> editor.putInt(key, obj)
        is Long -> editor.putLong(key, obj)
        else -> editor.putString(key, obj as String)
    }
    editor.apply()
}

/**
 * 获取数据
 */
fun Context.get(key: String, defaultObj: Any): Any? {
    return when (defaultObj) {
        is Boolean -> sp.getBoolean(key, defaultObj)
        is Float -> sp.getFloat(key, defaultObj)
        is Int -> sp.getInt(key, defaultObj)
        is Long -> sp.getLong(key, defaultObj)
        is String -> sp.getString(key, defaultObj)
        else -> null
    }
}

/**
 * 删除指定数据
 */
fun Context.remove(key: String) {
    val editor = sp.edit()
    editor.remove(key)
    editor.apply()
}

/**
 * 删除所有数据
 */
fun Context.clear() {
    val editor = sp.edit()
    editor.clear()
    editor.apply()
}

/**
 * 返回所有键值对
 */
fun Context.getAll(): Map<String, *> {
    return sp.all
}

/**
 * 检查key对应的数据是否存在
 */
fun Context.contains(key: String): Boolean {
    return sp.contains(key)
}
