package com.mackwu.anim.tween

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.AlphaAnimation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import com.mackwu.anim.R
import kotlinx.android.synthetic.main.tween_activity.*

class TweenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tween_activity)

        btn_alpha.setOnClickListener { alphaAnim() }
        btn_scale.setOnClickListener { scaleAnim() }
        btn_translate.setOnClickListener { translateAnim() }
        btn_rotate.setOnClickListener { rotateAnim() }
        btn_anim_attr.setOnClickListener { animAttr() }
    }

    /**
     * 透明度
     * AlphaAnimation(float fromAlpha, float toAlpha)
     * fromAlpha
     */
    private fun alphaAnim(){
        val animation = AlphaAnimation(1f, 0f)
        animation.duration = 2000
        iv_test.startAnimation(animation)
    }

    /**
     * 缩放
     */
    private fun scaleAnim(){
        val animation = ScaleAnimation(0f, 10f, 0f, 10f)
        animation.duration = 2000
        iv_test.startAnimation(animation)
    }

    /**
     * 位移
     */
    private fun translateAnim(){
        val animation = TranslateAnimation(0f, 20f, 0f, 20f)
        animation.duration = 2000
        iv_test.startAnimation(animation)
    }

    /**
     * 旋转
     */
    private fun rotateAnim(){
        val animation = RotateAnimation(0f, 90f)
        animation.duration = 2000
        iv_test.startAnimation(animation)
    }


    private fun animAttr(){
        val animation = AlphaAnimation(1f, 0f)

        // 动画延迟时间
//        animation.startOffset = 2000
        // 动画运行时间
        animation.duration = 2000
        // 设置动画结束后保持结束状态。默认会还原到动画开始时的状态
//        animation.fillAfter = true
        iv_test.startAnimation(animation)



        // 动画实际开始时间。不起作用？
//        animation.startTime = System.currentTimeMillis() + 3_000
        // 不起作用？
//        iv_test.animation = animation
    }

}