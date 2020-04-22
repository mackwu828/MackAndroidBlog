package com.mackwu.view.basic.webview

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * ===================================================
 * Created by MackWu on 2020/3/25 14:20
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class DefaultWebViewClient : WebViewClient() {

    /**
     * 返回true，在app中加载网页。否则会在浏览器中加载
     */
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        Log.d(TAG, "shouldOverrideUrlLoading...")
        view?.loadUrl(url)
        return true
    }

    /**
     * 开始加载网页时回调，用来开启加载动画
     */
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d(TAG, "onPageStarted...")
    }

    /**
     * 结束加载网页时回调，用来关闭加载动画
     */
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        Log.d(TAG, "onPageFinished...")
    }

    companion object {
        private val TAG = DefaultWebViewClient::class.java.simpleName
    }
}