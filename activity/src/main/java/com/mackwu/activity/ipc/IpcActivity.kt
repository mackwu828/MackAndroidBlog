package com.mackwu.activity.ipc

import android.content.ComponentName
import android.content.Intent
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
 *
 * 跳转到另一个进程的Activity
 */
class IpcActivity  : AppCompatActivity() {

    companion object {
        private const val PACKAGE_NAME = "com.mackwu.service"
        private const val CLASS_NAME = "com.mackwu.service.MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { startWithLaunchIntent() }
    }


    /**
     * 跳转到另一个进程指定的Activity
     * 1. 包名
     * 2. Activity名称
     * 3，AndroidManifest中Activity的exported属性要是true
     */
    private fun start() {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
                    .apply { addCategory(Intent.CATEGORY_LAUNCHER) }
                    .apply { component = ComponentName(PACKAGE_NAME, CLASS_NAME) }
            startActivity(intent)
        } catch (e: Exception) {
            // Unable to find explicit activity class {com.google.android.youtube.tv/com.google.android.youtube.UrlActivity}
            // Permission Denial: starting Intent ... not exported from uid 10066
            e.printStackTrace()
        }
    }

    /**
     * 跳转到另一个进程的MainActivity
     * 1. 包名
     * 2. 通过getLaunchIntentForPackage
     */
    private fun startWithLaunchIntent() {
        val intent = packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        intent?.run {
            // 默认的flags
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }

    
}