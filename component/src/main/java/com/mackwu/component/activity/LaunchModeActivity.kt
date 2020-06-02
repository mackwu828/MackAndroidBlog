package com.mackwu.component.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import kotlinx.android.synthetic.main.activity_test.*

/**
 * ================================================
 * Created by MackWu on 2019/8/22 17:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class LaunchModeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btn_test.setOnClickListener { startActivity(Intent(this, LaunchModeActivity::class.java)) }
    }
}