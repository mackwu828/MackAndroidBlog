package com.mackwu.performance.memory

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.performance.R

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 15:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class SingleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
