package com.mackwu.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.http.R

/**
 * ===================================================
 * Created by MackWu on 2019/10/9 11:57
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * Http
 * Http1.1及之前的协议中，一个连接只能同时支持单个流？
 * Http2.0协议，可以支持多个流
 * SPDY?
 *
 */
class HttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}