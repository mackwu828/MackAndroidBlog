package com.mackwu.component.provider

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.provider.ob.StudentObserver

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 17:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class StudentActivity : AppCompatActivity() {

    private val studentObserver = StudentObserver(this, Handler())
    private val studentUri = "content://com.mackwu.component.provider.StudentProvider/query"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // registerContentObserver
        contentResolver.registerContentObserver(Uri.parse(studentUri), true, studentObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        // unregisterContentObserver
        contentResolver.unregisterContentObserver(studentObserver)
    }
}