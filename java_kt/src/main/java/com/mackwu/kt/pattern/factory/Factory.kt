package com.mackwu.kt.pattern.factory

/**
 * ===================================================
 * Created by MackWu on 2020/1/3 1:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * 抽象工厂类
 */
interface Factory{

    /**
     * 创建产品。参数可以是String、Enum、Class
     */
    fun <T : Product> createProduct(productClass: Class<T>): T

}
