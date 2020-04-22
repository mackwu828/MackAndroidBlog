package com.mackwu.view.basic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R
import kotlinx.android.synthetic.main.activity_dialog.*

/**
 * ===================================================
 * Created by MackWu on 2020/1/20 15:25
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class DialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)

        btn_dialog.setOnClickListener {
        }
    }


}