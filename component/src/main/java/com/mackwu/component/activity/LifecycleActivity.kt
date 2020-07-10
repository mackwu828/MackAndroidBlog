package com.mackwu.component.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * ===================================================
 * Created by MackWu on 2020/1/20 16:34
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
open class LifecycleActivity : AppCompatActivity() {

    protected val currentClassName = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(currentClassName, "onCreate...")
    }

    override fun onStart() {
        super.onStart()
        Log.d(currentClassName, "onStart...")
    }

    override fun onResume() {
        super.onResume()
        Log.d(currentClassName, "onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.d(currentClassName, "onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.d(currentClassName, "onStop...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(currentClassName, "onDestroy...")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(currentClassName, "onRestart...")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(currentClassName, "onSaveInstanceState...")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(currentClassName, "onRestoreInstanceState...")
    }

}