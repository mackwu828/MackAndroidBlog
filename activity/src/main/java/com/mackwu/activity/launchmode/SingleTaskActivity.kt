package com.mackwu.activity.launchmode

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R

/**
 * ================================================
 * Created by MackWu on 2019/8/22 17:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 如果要启动的Activity已经处于Activity栈中，则会将该Activity上面的所有实例移除出栈，然后复用该Activity实例
 */
class SingleTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}