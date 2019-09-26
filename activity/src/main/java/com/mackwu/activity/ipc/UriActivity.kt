package com.mackwu.activity.ipc

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R

/**
 * ================================================
 * Created by MackWu on 2019/8/23 15:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class UriActivity : AppCompatActivity() {

    companion object {
        private const val PACKAGE_NAME = "com.mackwu.service"
        private const val URI_STR = "https://www.mackwu.com/test?query=xxx"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * 跳转到另一个进程的MainActivity，并传递Uri参数
     */
    private fun startProcessWithUri() {
        val intent = packageManager.getLaunchIntentForPackage(PACKAGE_NAME)
        intent?.run {
            data = Uri.parse(URI_STR)
            startActivity(this)
        }
    }

}
