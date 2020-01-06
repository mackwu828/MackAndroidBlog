package com.mackwu.component.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.util.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start.*

/**
 * ================================================
 * Created by MackWu on 2019/8/21 16:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // startActivity
        btn_start.setOnClickListener { startActivityCls(TargetActivity::class.java) }
        // startActivityForResult
        btn_start_for_result.setOnClickListener { startActivityClsForResult(TargetActivity::class.java, 0x01) }
        // start_best
        btn_start_best.setOnClickListener { TargetActivity.start(this, "xxx") }

        // 启动另一个进程的Activity
        btn_start_package.setOnClickListener { startPackage("com.mackwu.demo", "com.mackwu.demo.TargetActivity") }
        btn_action.setOnClickListener { startAction("com.mackwu.action.TARGET") }
        btn_launch.setOnClickListener { startLaunch("com.mackwu.demo") }
        btn_deep_link.setOnClickListener { startDeepLink("com.mackwu.demo", "com.mackwu.demo.MainActivity", "") }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 0x01) {
            // todo
        }
    }

}
