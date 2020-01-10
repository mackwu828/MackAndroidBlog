package com.mackwu.kt.pattern.observer.onclick

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 19:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * view是被观察者。当点击时(状态发生变化)，去通知所有的OnClickListener，这里只有一个。
 */
class MyView{

    fun setOnClickListener(l: OnClickListener) {
        l.onClick(this)
    }
}

//fun main() {
//    val myView = MyView()
//    myView.setOnClickListener(object : OnClickListener {
//        override fun onClick(v: MyView) {
//
//        }
//    })
//}
