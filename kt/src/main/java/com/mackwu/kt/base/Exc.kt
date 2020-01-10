package com.mackwu.kt.base

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 11:56
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
fun exc1() {
    throw Exception("xxx")
}


/**
 * try是一个表达式
 */
fun exc2() = try { val a = 2 } catch (e: Exception) { e.printStackTrace() }