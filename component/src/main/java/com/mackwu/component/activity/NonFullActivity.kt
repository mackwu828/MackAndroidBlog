package com.mackwu.component.activity

import android.os.Bundle
import com.mackwu.component.R
import com.mackwu.component.activity.lifecycle.LifecycleActivity

/**
 * ===================================================
 * Created by MackWu on 2020/1/20 15:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class NonFullActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val attributes = window.attributes
        // 设置窗口之外部分透明程度
        attributes.dimAmount = 0.0f
        attributes.x = 0
        attributes.y = 0
        attributes.width = 500
        attributes.height = 300
        window.attributes = attributes
        // 响应activity对窗口之外的触摸消息
//        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
    }
}