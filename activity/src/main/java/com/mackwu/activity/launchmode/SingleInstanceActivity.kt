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
 * 无论从哪个任务栈中启动目标Activity，都只会创建一个目标Activity的实例，并会使用一个全新的任务栈来装载该Activity的实例
 */
class SingleInstanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}