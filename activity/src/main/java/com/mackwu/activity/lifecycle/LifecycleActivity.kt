package com.mackwu.activity.lifecycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R

/**
 * ===================================================
 * Created by MackWu on 2019/9/30 14:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}