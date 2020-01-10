package com.mackwu.http.jetpack.lifecycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.http.R

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 19:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * AppCompatActivity的父类SupportActivity已经实现了LifecycleOwner，创建了lifecycleRegistry
 */
class LifecycleActivity : AppCompatActivity() {

    private val myLifecycleObserver = MyLifecycleObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("TAG", "onCreate...")

        //
        lifecycle.addObserver(myLifecycleObserver)
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

}