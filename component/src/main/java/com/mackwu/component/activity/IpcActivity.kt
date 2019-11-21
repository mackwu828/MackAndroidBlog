package com.mackwu.component.activity

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.activity.start.TargetActivity.Companion.start
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/22 19:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 启动另一个进程的Activity
 */
class IpcActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { start() }
    }

    /**
     * 通过包名和类名启动
     */
    private fun start() {
        try {
            val intent = Intent().apply { component = ComponentName("com.mackwu.ipc", "com.mackwu.ipc.MainActivity") }
            startActivity(intent)
        } catch (e: Exception) {
            // 如果启动的包名或者类名不存在时，会报错。Unable to find explicit activity class {com.mackwu.ipc/com.mackwu.ipc.MainActivity}
            e.printStackTrace()
        }
    }

    /**
     * 通过action启动
     */
    private fun start2() {
        try {
            val intent = Intent("com.mackwu.ipc.action.TARGET")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    /**
     * 通过包名和getLaunchIntentForPackage启动
     * 会启动另一个进程的主activity
     */
    private fun start3() {
        val intent = packageManager.getLaunchIntentForPackage("com.mackwu.ipc")
        intent?.run {
            startActivity(this)
        }
    }


    /**
     * deepLink
     * 使用URL SCHEMES
     */
    private fun deepLink() {
        try {
            val intent = Intent()
                    .apply { action = Intent.ACTION_MAIN }
                    .apply { addCategory(Intent.CATEGORY_LAUNCHER) }
                    .apply { component = ComponentName("com.mackwu.ipc", "com.mackwu.ipc.activity.DeepLinkActivity") }
                    .apply { data = Uri.parse("https://www.mackwu.com/test?query=xxx") }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}