package com.mackwu.anim

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.anim.sample.DotsActivity
import com.mackwu.anim.sample.WaterRippleActivity
import com.mackwu.anim.tween.InterpolatorActivity
import com.mackwu.anim.tween.TweenActivity
import com.mackwu.anim.util.startActivityCls
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tween
        btn_tween.setOnClickListener { startActivityCls(TweenActivity::class.java) }
        btn_interpolator.setOnClickListener { startActivityCls(InterpolatorActivity::class.java) }

        //
        btn_water_ripple.setOnClickListener { startActivityCls(WaterRippleActivity::class.java) }
        btn_dots.setOnClickListener {  startActivityCls(DotsActivity::class.java) }

    }
}
