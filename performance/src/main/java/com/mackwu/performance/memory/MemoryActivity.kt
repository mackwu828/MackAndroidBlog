package com.mackwu.performance.memory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.mackwu.performance.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2020/1/7 15:15
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class MemoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener { Singleton.getInstance(this) }
    }
}
