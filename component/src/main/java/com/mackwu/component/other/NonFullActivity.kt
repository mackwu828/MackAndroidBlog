package com.mackwu.component.other

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.util.setNonFullActivity

/**
 * ===================================================
 * Created by MackWu on 2020/1/20 15:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class NonFullActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNonFullActivity(600, 400)
    }
}