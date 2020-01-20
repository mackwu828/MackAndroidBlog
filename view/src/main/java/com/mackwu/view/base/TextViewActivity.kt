package com.mackwu.view.base

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R
import kotlinx.android.synthetic.main.activity_text_view.*

/**
 * ================================================
 * Created by MackWu on 2019/9/6 14:29
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class TextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)
        tv_test.isSelected = true
    }
}