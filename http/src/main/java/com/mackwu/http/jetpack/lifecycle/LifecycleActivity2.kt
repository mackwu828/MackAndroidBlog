package com.mackwu.http.jetpack.lifecycle

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.http.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2019/12/23 19:09
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * AppCompatActivity的父类SupportActivity已经实现了LifecycleOwner，创建了lifecycleRegistry
 */
class LifecycleActivity2  : AppCompatActivity(){

    private val myLifecycleObserver = MyLifecycleObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(myLifecycleObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(myLifecycleObserver)
    }
}