package com.mackwu.anim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import kotlinx.android.synthetic.main.activity_anim.*

/**
 * ================================================
 * Created by MackWu on 2019/8/16 16:02
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class AnimActivity : AppCompatActivity() {

    private var isMenuOpen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anim)


        val animation = TestAnimation()
        animation.duration = 1000
        //
        btn_test.setOnClickListener {
            isMenuOpen = !isMenuOpen
            ll_main.startAnimation(animation)
        }
    }

    inner class TestAnimation : Animation() {

        /**
         * @param interpolatedTime The value of the normalized time (0.0 to 1.0)
         */
        override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
            val layoutParams = ll_main.layoutParams as ViewGroup.MarginLayoutParams
            val menuWidth = ll_menu.width
            val mainLeftMargin = layoutParams.leftMargin
            val menuIconWidth = 40
            if (!isMenuOpen) {
                layoutParams.leftMargin = (mainLeftMargin + ((menuWidth - mainLeftMargin) * interpolatedTime)).toInt()
            } else {
                layoutParams.leftMargin = (mainLeftMargin - (mainLeftMargin - menuIconWidth) * interpolatedTime).toInt()
            }
            Log.d("TAG", "layoutParams.leftMargin: " + layoutParams.leftMargin)
            ll_main.layoutParams = layoutParams
        }
    }

}