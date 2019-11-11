package com.mackwu.kt.util

import android.content.Context
import android.support.v4.app.Fragment

/**
 * ===================================================
 * Created by MackWu on 2019/9/25 14:21
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
private val Context.spName
    get() = packageName + "_preferences"

/**
 * 保存数据
 */
fun Context.put(key: String, obj: Any) {
    val sp = getSharedPreferences(spName, Context.MODE_PRIVATE)
    val editor = sp.edit()
    when (obj) {
        is Boolean -> editor.putBoolean(key, obj)
        is Float -> editor.putFloat(key, obj)
        is Int -> editor.putInt(key, obj)
        is Long -> editor.putLong(key, obj)
        else -> editor.putString(key, obj as String)
    }
    editor.apply()
    editor.commit()
}


/**
 * 获取数据
 */
fun Context.get(key: String, defaultObj: Any): Any? {
    val sp = getSharedPreferences(spName, Context.MODE_PRIVATE)
    return when (defaultObj) {
        is Boolean -> sp.getBoolean(key, defaultObj)
        is Float -> sp.getFloat(key, defaultObj)
        is Int -> sp.getInt(key, defaultObj)
        is Long -> sp.getLong(key, defaultObj)
        is String -> sp.getString(key, defaultObj)
        else -> null
    }
}

fun Fragment.put(key: String, obj: Any) {
    activity?.put(key, obj)
}

fun Fragment.get(key: String, defaultObj: Any): Any? {
    return activity?.get(key, defaultObj)
}

///**
// * 删除指定数据
// */
//fun Context.remove(context: Context, key: String) {
//    val sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
//    val editor = sp.edit()
//    editor.remove(key)
//    editor.commit()
//}
//
//
///**
// * 返回所有键值对
// */
//fun Context.getAll(context: Context): Map<String, *> {
//    val sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
//    return sp.all
//}
//
///**
// * 删除所有数据
// */
//fun Context.clear(context: Context) {
//    val sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
//    val editor = sp.edit()
//    editor.clear()
//    editor.commit()
//}
//
///**
// * 检查key对应的数据是否存在
// */
//fun Context.contains(context: Context, key: String): Boolean {
//    val sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
//    return sp.contains(key)
//}
