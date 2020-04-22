package com.mackwu.adapter.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * ===================================================
 * Created by MackWu on 2020/4/2 18:07
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
abstract class BaseActivity: AppCompatActivity() {

    /**
     * 布局id
     */
    abstract val layoutId: Int

    /**
     * 初始化
     */
    abstract fun initView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initView()
    }
}