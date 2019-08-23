package com.mackwu.activity.start

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/23 17:14
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * 启动Activity的最佳方式
 * 在要跳转的Activity中提供一个静态方法
 */
class StartBestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 启动Activity的最佳方式
        btn_test.setOnClickListener { SecondActivity.start(this, "xxx") }
    }
}