package com.mackwu.view.basic.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/9 11:05
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        web_view.webViewClient = DefaultWebViewClient()
        val url = "https://www.baidu.com"

        btn_test.setOnClickListener { web_view.loadUrl(url) }
    }
}