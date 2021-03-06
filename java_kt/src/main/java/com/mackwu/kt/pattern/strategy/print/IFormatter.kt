package com.mackwu.kt.pattern.strategy.print

/**
 * ===================================================
 * Created by MackWu on 2020/3/6 16:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface IFormatter {

    val format: (String) -> String
}