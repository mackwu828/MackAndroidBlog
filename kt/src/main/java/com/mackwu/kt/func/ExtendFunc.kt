package com.mackwu.kt.func

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 9:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 扩展函数
 * 扩展一个类的新功能而无需继承该类或者使用像装饰者这样的设计模式
 *
 * "fun 接收者.函数名(参数列表)" 接收者可以是类名、伴生对象
 */
 class ExtendFunc


fun Activity.startActivity(cls: Class<*>){
    startActivity(Intent(this, cls))
}