package com.mackwu.component.ui.other

import android.os.Bundle
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2020/2/17 15:45
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class SaveInstanceStateActivity : LifecycleActivity() {

    private val text = "exception occurred"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("text", text)
    }

}