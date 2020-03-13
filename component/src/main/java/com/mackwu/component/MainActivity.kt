package com.mackwu.component

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.activity.NoStatusBarActivity
import com.mackwu.component.activity.NonFullActivity
import com.mackwu.component.activity.TargetActivity
import com.mackwu.component.util.*
import kotlinx.android.synthetic.main.activity_test.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // 启动方式：基础
        btn_start.setOnClickListener { startActivityCls(TargetActivity::class.java) }
        btn_start_for_result.setOnClickListener { startActivityClsForResult(TargetActivity::class.java, 0x01) }
        btn_start_best.setOnClickListener { TargetActivity.start(this, "xxx") }

        // 启动方式：启动另一个进程的Activity
        btn_start_package.setOnClickListener { startPackageActivity("com.mackwu.demo", "com.mackwu.demo.MainActivity") }
        btn_start_action.setOnClickListener { startActionActivity("com.mackwu.action.TARGET_ACTIVITY") }
        btn_start_launch.setOnClickListener { startLaunchActivity("com.mackwu.xxx") }

        // 样式
        btn_non_full.setOnClickListener { startActivityCls(NonFullActivity::class.java) }
        btn_no_status_bar.setOnClickListener { startActivityCls(NoStatusBarActivity::class.java) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 0x01) {
            // todo
        }
    }

}