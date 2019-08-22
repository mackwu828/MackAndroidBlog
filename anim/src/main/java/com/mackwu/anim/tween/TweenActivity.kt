package com.mackwu.anim.tween

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.animation.*
import com.mackwu.anim.R
import kotlinx.android.synthetic.main.tween_activity.*

class TweenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tween_activity)

        // XML中实现透明度、缩放、位移、旋转
        btn_alpha_xml.setOnClickListener { alphaXmlAnim() }
        btn_scale_xml.setOnClickListener { scaleXmlAnim() }
        btn_translate_xml.setOnClickListener { translateXmlAnim() }
        btn_rotate_xml.setOnClickListener { rotateXmlAnim() }

        // 代码中实现
        btn_alpha.setOnClickListener { alphaAnim() }
        btn_scale.setOnClickListener { scaleAnim() }
        btn_translate.setOnClickListener { translateAnim() }
        btn_rotate.setOnClickListener { rotateAnim() }

        //
        btn_anim_attr.setOnClickListener { animAttr() }
    }


    private fun alphaXmlAnim(){
        val animation = AnimationUtils.loadAnimation(this, R.anim.alpha_anim)
        iv_test.startAnimation(animation)
    }

    private fun scaleXmlAnim(){
    }

    private fun translateXmlAnim(){
    }

    private fun rotateXmlAnim(){
    }

    /**
     * 透明度
     */
    private fun alphaAnim(){
        // fromAlpha动画起始时透明度，toAlpha动画结束时透明度。0f透明
        val animation = AlphaAnimation(1f, 0f)
        // 动画持续时间
        animation.duration = 2000
        Log.d("TAG", animation.startTime.toString())
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

    private fun animListener(){
        val animation = AlphaAnimation(1f, 0f)
        animation.duration = 2000
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {
                //动画重复时执行
            }

            override fun onAnimationEnd(animation: Animation?) {
                //动画结束时执行
            }

            override fun onAnimationStart(animation: Animation?) {
                //动画开始时执行
            }

        })
        iv_test.startAnimation(animation)
    }
}
