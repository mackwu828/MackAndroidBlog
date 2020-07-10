package com.mackwu.java_kt.kt.pattern.singleton

import android.content.Context
import com.mackwu.java_kt.kt.pattern.singleton.holder.SingletonHolder

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