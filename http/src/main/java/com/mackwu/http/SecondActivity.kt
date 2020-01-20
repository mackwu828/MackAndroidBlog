package com.mackwu.http

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.http.okhttp_retrofit_rxjava.ApiHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2020/1/6 19:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        btn_test.setOnClickListener { ApiHelper.getIp {} }
    }
}
