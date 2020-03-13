package com.mackwu.anim.util

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import androidx.core.view.ViewCompat

/**
 * ===================================================
 * Created by MackWu on 2020/3/12 14:28
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
fun View.startTranslation() {

}


/**
 * 缩放动画
 */
fun View.startScale(x: Float, y: Float) {
    val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, x)
    val scaleY = PropertyValuesHolder.ofFloat("scaleX", 1f, y)
    ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY).start()
}

fun View.a() {
    ViewCompat.animate(this)
            .setDuration(200)
            .scaleX(1f)
            .scaleY(1f)
            .start()
}