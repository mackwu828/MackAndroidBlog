package com.mackwu.anim

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.Transformation
import com.mackwu.anim.tween.InterpolatorActivity
import com.mackwu.anim.tween.TweenActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // tween
        btn_tween.setOnClickListener { startActivity(Intent(this, TweenActivity::class.java)) }
        btn_interpolator.setOnClickListener { startActivity(Intent(this, InterpolatorActivity::class.java)) }

    }
}
