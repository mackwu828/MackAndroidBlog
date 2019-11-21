package com.mackwu.ipc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.mackwu.ipc.R

/**
 * ================================================
 * Created by MackWu on 2019/9/4 10:45
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 跳转到另一个进程指定的Activity，并传递Uri参数
 *
 * 目标Activity需要在AndroidManifest.xml配置intent-filter
 * @see AndroidManifest.xml
 * <intent-filter>
 *      <data android:scheme="http" />
 *      <data android:scheme="https" />
 *      <data android:host="www.mackwu.com" />
 *      <data android:path="/test" />
 *
 *      <action android:name="android.intent.action.VIEW" />
 *      <category android:name="android.intent.category.DEFAULT" />
 *      <category android:name="android.intent.category.BROWSABLE" />
 * </intent-filter>
 *
 */
class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = intent.data
        // uri = https://www.mackwu.com/test?query=xxx
        // path: /test, query: xxx
        Log.d("TAG", "path: ${uri?.path}, query: ${uri?.getQueryParameter("query")}")
    }
}