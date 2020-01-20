package com.mackwu.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_x5.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/9 11:55
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class X5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_x5)
        btn_test.setOnClickListener {
            val url = "https://www.baidu.com"
            x5_web_view.loadUrl(url)
            x5_web_view.webViewClient = object : WebViewClient() {

            }
        }
    }

}