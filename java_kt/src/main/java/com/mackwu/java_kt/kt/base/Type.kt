package com.mackwu.java_kt.base

/**
 * ===================================================
 * Created by MackWu on 2019/10/17 10:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 基本类型
 *
 * 整数：
 * Byte:    8bits       -128~127
 * Shore:   16bits      -32768~32767
 * Int:     32bits      -2^31~2^31-1
 * Long:    64bits      -2^63~2^63-1
 * 注1：小等于Int最大值的整数编译器都会推断为Int类型，如果超过Int最大值，则推断为Long类型，如果要显示指定为Long类型，需要加l或L后缀
 *
 * 浮点数：
 * Float:   32bits
 * Double:  64bits
 * 注1：有小数的变量编译器都会推断为Double类型，如果要显示指定为Float类型，需要加f或F后缀。如val a = 1.1f
 * 注2：如果Float类型包含多于 6～7 位十进制数，那么会将其舍入。 如val a = 2.7182818284f // 实际值为 2.7182817
 *
 * 字符：
 * Char
 * 注1：字符不能当做数字。 if('A' == 1) // 编译器报错：类型不兼容
 *
 * 布尔值：
 * Boolean: true、false
 *
 *
 * <h2>数字不支持隐式转换</h2>
 * 与Java不同，数字不支持隐式转换。
 * 例：如果一个函数要求传入Double类型，传入Int、Float等类型时编译器会报错，需要通过[toDouble]显示转换为Double类型
 * fun printDouble(d: Double) { println(d) }
 * val a = 1
 * printDouble(a.toDouble())
 *
 *
 * <h2>数字中的下划线</h2>
 * 从Kotlin1.1起，可以用下划线使数字更易读。如val oneMillion = 1_000_000
 *
 *
 * <h2>===和==区别</h2>
 *
 *
 * <h2>运算</h2>
 *
 */

fun main() {
}


