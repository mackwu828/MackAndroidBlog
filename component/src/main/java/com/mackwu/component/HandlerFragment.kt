package com.mackwu.component

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import com.mackwu.component.fragment.base.SimpleBaseFragment

/**
 * ===================================================
 * Created by MackWu on 2020/5/26 17:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class HandlerFragment : SimpleBaseFragment() {

    @SuppressLint("HandlerLeak")
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {

                }
            }
        }
    }

    override val layoutId: Int = R.layout.activity_test

    override fun initView() {
        Thread {
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            handler.sendEmptyMessage(1)
        }.start()
    }
}