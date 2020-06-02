package com.mackwu.component.jetpack.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import kotlinx.android.synthetic.main.activity_test.*

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
        setContentView(R.layout.activity_test)

        btn_test.setOnClickListener {  }
    }
}