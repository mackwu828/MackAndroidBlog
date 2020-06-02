package com.mackwu.component.other

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.util.addServiceListener
import com.mackwu.component.util.removeServiceListener
import kotlinx.android.synthetic.main.activity_test.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/4 16:20
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class JmDnsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btn_test.setOnClickListener { addServiceListener() }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeServiceListener()
    }
}