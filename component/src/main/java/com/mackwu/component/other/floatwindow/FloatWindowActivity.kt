package com.mackwu.component.other.floatwindow

import android.os.Bundle
import com.mackwu.component.R
import com.mackwu.component.base.BaseActivity
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

    override fun getLayoutId(): Int = R.layout.activity_flow_window

    override fun initView(savedInstanceState: Bundle?) {
        btn_show_float_window.setOnClickListener { floatWindow.show() }
    }

    override fun initData(savedInstanceState: Bundle?) {
    }
}