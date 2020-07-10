package com.mackwu.java_kt.kt.pattern.factory

/**
 * ===================================================
 * Created by MackWu on 2020/1/3 1:21
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 具体工厂类
 */
class ConcreteFactory : Factory{

    override fun <T : Product> createProduct(productClass: Class<T>): T{
        return Class.forName(productClass.name).newInstance() as T
    }
}

fun main() {
    val concreteFactory = ConcreteFactory()
    val productA = concreteFactory.createProduct(ProductA::class.java)
    val productB = concreteFactory.createProduct(ProductB::class.java)
}