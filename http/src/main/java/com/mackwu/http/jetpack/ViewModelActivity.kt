package com.mackwu.http.jetpack

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.http.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 19:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class ViewModelActivity  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ipViewModel = ViewModelProviders.of(this).get(IpViewModel::class.java)

        btn_test.setOnClickListener {  }
    }
}