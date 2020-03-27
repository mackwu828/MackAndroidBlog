package com.mackwu.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.base.webview.WebViewActivity
import com.mackwu.view.util.startActivityCls
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_webview.setOnClickListener { startActivityCls(WebViewActivity::class.java) }
    }
}
