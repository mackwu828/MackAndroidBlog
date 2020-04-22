package com.mackwu.anim.util

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.widget.TextView

/**
 * ===================================================
 * Created by MackWu on 2020/4/14 14:08
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * 启动...动画
 */
@SuppressLint("SetTextI18n")
fun TextView.startDotsAnim(loadingText: String = "加载中", dotCount: Int = 4){
    val dots = when (dotCount) {
        4 ->    arrayListOf(".      ", ". .    ", ". . .  ", ". . . .")
        else -> arrayListOf(".    ", ". .  ", ". . .")
    }
    val valueAnimator = ValueAnimator.ofInt(0, dots.size).setDuration(2500)
    valueAnimator.repeatCount = ValueAnimator.INFINITE
    valueAnimator.addUpdateListener { animation ->
        val i = animation.animatedValue as Int
        text = loadingText + dots[i % dots.size]
    }
    valueAnimator.start()
}