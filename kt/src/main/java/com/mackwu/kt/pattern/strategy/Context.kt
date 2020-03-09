package com.mackwu.kt.pattern.strategy

/**
 * ===================================================
 * Created by MackWu on 2020/2/26 1:54
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
// 持有策略接口
class Context(val strategy: Strategy) {

    fun doAnything(){
        strategy.doSomething()
    }
}

fun main() {
    val strategy = StrategyA()
    val context = Context(strategy)
    context.doAnything()

    // 算法自由切换
    Context(StrategyA()).doAnything()
    Context(StrategyB()).doAnything()
}