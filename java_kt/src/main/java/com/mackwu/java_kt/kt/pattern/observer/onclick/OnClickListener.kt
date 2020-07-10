package com.mackwu.java_kt.kt.pattern.observer.onclick

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 14:32
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * OnClickListener是观察者。
 */
interface OnClickListener {
    fun onClick(v: MyView)
}