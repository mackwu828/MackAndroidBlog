package com.mackwu.kt.pattern.singleton

import android.content.Context

/**
 * ===================================================
 * Created by MackWu on 2020/1/15 14:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class ContextSingleton private constructor(context: Context){

    companion object: SingletonHolder<ContextSingleton, Context>(::ContextSingleton)
}