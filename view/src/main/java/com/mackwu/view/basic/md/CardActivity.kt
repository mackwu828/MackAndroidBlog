package com.mackwu.view.basic.md

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R

/**
 * ================================================
 * Created by MackWu on 2019/9/6 15:24
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * CardView
 * 一个带圆角和阴影背景的FrameLayout。圆角和阴影效果也可以用shape+elevation实现。
 *
 * 需要依赖'com.android.support:design:28.0.0'
 *
 *
 */
class CardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
    }
}