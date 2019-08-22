package com.mackwu.activity.start

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
 */
class StartProcessActivity  : AppCompatActivity() {

    companion object {
        private const val PACKAGE_NAME = "com.mackwu.service"
        private const val CLASS_NAME = "com.mackwu.service.MainActivity"
        private const val URI_STR = "https://www.mackwu.com/test?query=xxx"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { startProcessWithUri() }
    }


    /**
     * 跳转到另一个进程的指定Activity
     * 1. 需要知道另一个进程的包名
     * 2. 需要知道Activity的类名，且Activity的exported属性要是true。类名不存在时或者exported=false时会报错
     */
    private fun startProcess() {
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
     * 跳转到另一个进程的主页面
     * 需要知道另一个进程的包名。getLaunchIntentForPackage等同于设置action和category
     */
    private fun startProcess2() {
        val intent = packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        intent?.run {
            // 默认的flags
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(this)
        }
    }


    /**
     * 跳转到另一个进程的主页面，并传递Uri参数
     */
    private fun startProcessWithUri() {
        val intent = packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        intent?.run {
            data = Uri.parse(URI_STR)
            startActivity(this)
        }
    }
    
}