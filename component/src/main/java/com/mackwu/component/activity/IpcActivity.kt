package com.mackwu.component.activity

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/22 19:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 跳转到另一个进程的Activity
 *
 * <h2>包名+类名</h2>
 * 要求1：需要知道另一个进程的包名
 * 要求2：需要知道另一个进程的Activity名称，且Activity的exported属性要是true
 * @see start
 *
 * 异常1：另一个进程的Activity名称不存在时
 * Unable to find explicit activity class {com.google.android.youtube.tv/com.google.android.youtube.UrlActivity}
 * 异常2：另一个进程的Activity的exported属性不是true时
 * Permission Denial: starting Intent ... not exported from uid 10066
 *
 * <h2>自定义action</h2>
 * 要求1：需要知道另一个进程的activity中自定义的action名称，且Activity的exported属性要是true
 * @see startWithAction
 *
 * <h2>getLaunchIntentForPackage</h2>
 * 通过getLaunchIntentForPackage会跳转到另一个进程的<category android:name="android.intent.category.LAUNCHER" />的activity
 * 要求1：需要知道另一个进程的包名
 * @see startWithLaunchIntent
 *
 * <h2>DeepLink</h2>
 * 使用URL SCHEMES
 * @see startWithDeepLink
 *
 */
class IpcActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { start() }
    }

    private fun start() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
                    .apply { addCategory(Intent.CATEGORY_LAUNCHER) }
                    .apply { component = ComponentName("com.mackwu.ipc", "com.mackwu.ipc.activity.TargetActivity") }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startWithAction() {
        try {
            val intent = Intent("com.mackwu.ipc.action.TARGET")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startWithLaunchIntent() {
        val intent = packageManager.getLaunchIntentForPackage("com.mackwu.ipc")
        intent?.run {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }

    private fun startWithDeepLink() {
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