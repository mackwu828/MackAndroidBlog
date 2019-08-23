package com.mackwu.activity.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/21 16:40
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
    }

}
