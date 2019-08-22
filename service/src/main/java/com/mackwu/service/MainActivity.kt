package com.mackwu.service

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = intent.data
        Toast.makeText(this, uri?.getQueryParameter("query"), Toast.LENGTH_SHORT).show()
    }
}
