package com.mackwu.view.base

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
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
        btn_test.setOnClickListener {
            val url = "https://www.baidu.com"
            web_view.loadUrl(url)
            web_view.webViewClient = MyWebViewClient()
        }
    }

    class MyWebViewClient : WebViewClient() {

        /**
         * 返回true，在app中加载网页。否则会在浏览器中加载
         */
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        /**
         * 开始加载网页时回调，用来开启加载动画
         */
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        /**
         * 结束加载网页时回调，用来关闭加载动画
         */
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

}