package com.mackwu.component

import android.os.Bundle
import android.os.Environment
import android.os.Environment.getDataDirectory
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.component.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener {
            externalPath()

            buildInPath()
        }
    }
}