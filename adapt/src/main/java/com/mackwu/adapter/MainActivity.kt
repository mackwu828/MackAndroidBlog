package com.mackwu.adapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.adapter.core.ApiHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { ApiHelper.translate() }
    }
}
