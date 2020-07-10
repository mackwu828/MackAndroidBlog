package com.mackwu.java_kt.kt.pattern.strategy.print

import java.util.*

/**
 * ===================================================
 * Created by MackWu on 2020/3/6 16:14
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class LowerCaseFormatter : IFormatter {
    override val format: (String) -> String = { it.toLowerCase(Locale.getDefault()) }
}