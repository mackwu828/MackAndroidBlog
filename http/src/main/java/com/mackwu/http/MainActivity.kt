package com.mackwu.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.http.okhttp.OkHttpManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener {
            OkHttpManager.get("https://raw.github.com/square/okhttp/master/README.md"){

            }
        }
    }
}
