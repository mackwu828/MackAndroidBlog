package com.mackwu.kt.pattern

import android.text.TextUtils

/**
 * ===================================================
 * Created by MackWu on 2019/11/4 19:19
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * https://www.jianshu.com/p/8eb331f75bfa
 */

class Company(val name: String, val address: String) {

    constructor(builder: Builder) : this(builder.name, builder.address)

    class Builder {

        internal var name: String = ""
        internal var address: String = ""

        fun name(init: () -> String) {
            name = init()
        }

        fun address(init: () -> String) {
            address = init()
        }

        fun build(): Company {
            return Company(this)
        }
    }

}


/**
 * 用带接受者参数Company.Builder的高阶函数创建对象。那么每次调用company都带有Builder对象
 * buildCompany: Company.Builder.() -> Unit
 */
inline fun company(buildCompany: Company.Builder.() -> Unit): Company {
    val builder = Company.Builder()
    builder.buildCompany()
    return builder.build()
}


fun main() {
    val company = company {
        name {
            var a = 1
            if (a > 2) a = 2
            if (a < 2) a= 3
            a.toString()
        }
        address { "..." }
    }
}