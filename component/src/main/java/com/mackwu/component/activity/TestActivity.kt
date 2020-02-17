package com.mackwu.component.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.util.*
import kotlinx.android.synthetic.main.activity_test.*

/**
 * ================================================
 * Created by MackWu on 2019/8/21 16:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // startActivity
        btn_start.setOnClickListener { startActivityCls(TargetActivity::class.java) }
        // startActivityForResult
        btn_start_for_result.setOnClickListener { startActivityClsForResult(TargetActivity::class.java, 0x01) }
        // start_best
        btn_start_best.setOnClickListener { TargetActivity.start(this, "xxx") }

        // 启动另一个进程的Activity
        btn_start_package.setOnClickListener { startPackage("com.mackwu.demo", "com.mackwu.demo.MainActivity") }
        btn_action.setOnClickListener { startAction("com.mackwu.action.TARGET") }
        btn_launch.setOnClickListener { startLaunch("com.mackwu.xxx") }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0x01) {
            // todo
        }
    }

}
