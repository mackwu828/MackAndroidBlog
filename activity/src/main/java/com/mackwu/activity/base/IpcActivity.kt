package com.mackwu.activity.base

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/22 19:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 跳转到另一个进程的Activity
 *
 * <h2>跳转到另一个进程指定的Activity</h2>
 * 需要知道另一个进程的包名和Activity名称，且Activity的exported属性要是true，默认是false
 * @see start()
 * 异常1：另一个进程的Activity名称不存在时
 * Unable to find explicit activity class {com.google.android.youtube.tv/com.google.android.youtube.UrlActivity}
 * 异常2：另一个进程的Activity的exported属性不是true时
 * Permission Denial: starting Intent ... not exported from uid 10066
 *
 * <h2>跳转到另一个进程的MainActivity</h2>
 * 通过getLaunchIntentForPackage获取Intent，需要知道另一个进程的包名
 * @see startWithLaunchIntent()
 *
 * <h2>跳转到另一个进程的MainActivity，并传递Uri参数</h2>
 * 通过getLaunchIntentForPackage获取Intent，需要知道另一个进程的包名
 * @see startUri()
 */
class IpcActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { startWithLaunchIntent() }
    }


    /**
     * 跳转到另一个进程指定的Activity
     */
    private fun start() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
                    .apply { addCategory(Intent.CATEGORY_LAUNCHER) }
                    .apply { component = ComponentName("com.mackwu.service", "com.mackwu.service.MainActivity") }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 跳转到另一个进程的MainActivity
     */
    private fun startWithLaunchIntent() {
        val intent = packageManager.getLaunchIntentForPackage("com.mackwu.service")
        intent?.run {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }

    /**
     * 跳转到另一个进程的MainActivity，并传递Uri参数
     */
    private fun startUri() {
        val intent = packageManager.getLaunchIntentForPackage("com.mackwu.service")
        intent?.run {
            data = Uri.parse("https://www.mackwu.com/test?query=xxx")
            startActivity(this)
        }
    }

}