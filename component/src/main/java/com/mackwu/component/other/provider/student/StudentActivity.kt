package com.mackwu.component.other.provider.student

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 17:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class StudentActivity : AppCompatActivity() {

    private val studentObserver = StudentObserver(this, Handler())
    private val studentUri = "content://com.mackwu.component.provider.student.StudentProvider/query"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // registerContentObserver
        contentResolver.registerContentObserver(Uri.parse(studentUri), true, studentObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        // unregisterContentObserver
        contentResolver.unregisterContentObserver(studentObserver)
    }
}