package com.mackwu.component.other.floatwindow

import com.mackwu.component.R
import com.mackwu.component.activity.base.BaseActivity
import kotlinx.android.synthetic.main.activity_flow_window.*

/**
 * ===================================================
 * Created by MackWu on 2020/4/17 10:07
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class FloatWindowActivity : BaseActivity() {

    private val floatWindow = FloatWindow(this)

    override val layoutId: Int = R.layout.activity_flow_window

    override fun initView() {
        btn_show_float_window.setOnClickListener { floatWindow.show() }
    }
}