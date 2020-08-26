package com.mackwu.kt.base

/**
 * ===================================================
 * Created by MackWu on 2019/10/17 14:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 字符串
 *
 * <h2>原始字符串</h2>
 * 使用三个引号（"""）
 *     val text = """
 *          |Tell me and I forget.
 *          |Teach me and I remember.
 *          |Involve me and I learn.
 *          |(Benjamin Franklin)
 *      """.trimIndent()
 * 输出
 * |Tell me and I forget.
 * |Teach me and I remember.
 * |Involve me and I learn.
 * |(Benjamin Franklin)
 *
 *
 * <h2>字符串模板</h2>
 * 以美元符（$）开头
 *  val i = 10
 *  println("i = $i")  // 输出“i = 10”
 */
fun main() {
    val i = 10
    println("i = $i")
}
 