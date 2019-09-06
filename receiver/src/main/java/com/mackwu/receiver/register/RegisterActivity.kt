package com.mackwu.receiver.register

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.receiver.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/9/5 11:44
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 * 广播
 *
 * <h2>注册方式</h2>
 * 静态注册：在Manifest中注册，广播会跟随程序的生命周期，直到程序被销毁
 *
 * 动态注册：在代码中通过IntentFilter注册。如果在activity中注册，广播会跟随activity的生命周期，activity被销毁，广播也会被销毁
 * 注：需要在activity的onDestroy中反注册，防止内存泄漏
 *
 */
class RegisterActivity : AppCompatActivity() {

    private val receiver = MyReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 动态注册
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.mackwu.receiver.register.MyReceiver")
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}