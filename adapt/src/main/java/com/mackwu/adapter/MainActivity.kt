package com.mackwu.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.adapter.util.startActivityCls
import com.mackwu.adapter.version.PermissionActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        btn_runtime_permission.setOnClickListener { startActivityCls(PermissionActivity::class.java) }
    }
}
