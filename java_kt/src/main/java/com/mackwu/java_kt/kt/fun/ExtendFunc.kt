package com.mackwu.java_kt.kt.`fun`

import android.app.Activity
import android.content.Intent

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 9:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 扩展函数。为一个类定义一个新的函数，这个新增的函数就像类中的其他函数一样调用。"fun 接收者.属性名(参数列表)"
 * 一般定义为顶层函数，可以替代Java中的工具类
 * 实际并没有为这个类添加函数，具体查看字节码？
 */
fun Activity.startActivity(cls: Class<*>){
    startActivity(Intent(this, cls))
}