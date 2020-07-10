package com.mackwu.java_kt.kt.`fun`

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 10:17
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 内联函数
 * 不会去调用函数，而是会将函数替换到调用的位置。
 * 普通函数无需内联：JVM会自动进行内联优化，会在需要优化的地方自动将函数转化成内联函数。为了消除lambda带来的运行时开销。
 *
 * <h2>带有lambda参数的函数使用内联</h2>
 * <a href="https://zhuanlan.zhihu.com/p/52068802">Effective Kotlin系列之探索高阶函数中inline修饰符</a>
 *
 */
