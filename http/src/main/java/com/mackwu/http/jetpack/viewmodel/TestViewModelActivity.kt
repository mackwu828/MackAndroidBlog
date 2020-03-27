package com.mackwu.http.jetpack.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mackwu.http.R
import com.mackwu.http.util.toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 19:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class TestViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ipViewModel = ViewModelProviders.of(this).get(IpViewModel::class.java)
        ipViewModel.ipLiveData.observe(this, Observer { ip -> toast(ip.toString()) })

        btn_test.setOnClickListener { ipViewModel.getIp() }
    }
}