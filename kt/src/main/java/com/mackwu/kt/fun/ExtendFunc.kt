package com.mackwu.kt.`fun`

import android.app.Activity
import android.content.Intent

/**
 * ===================================================
 * Created by MackWu on 2019/10/22 9:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 扩展函数
 * 为一个类定义一个新的函数，这个新增的函数就像类中的其他函数一样调用。一般定义为顶层函数，可以替代Java中的工具类
 * "fun 类名.函数名(参数列表)"  如 fun Activity.startActivity(cls: Class<*>)
 *
 *
 * <h2>扩展属性</h2>
 * "fun 接收者.属性名(参数列表)"
 */
// 扩展Activity的跳转
fun Activity.startActivity(cls: Class<*>){
    startActivity(Intent(this, cls))
}