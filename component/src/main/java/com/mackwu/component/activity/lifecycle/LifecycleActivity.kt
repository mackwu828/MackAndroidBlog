package com.mackwu.component.activity.lifecycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2019/9/30 14:16
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "onCreate...")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart...")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("TAG", "onRestart...")
    }

}