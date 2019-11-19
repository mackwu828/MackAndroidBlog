package com.mackwu.component.provider

import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mackwu.component.R
import com.mackwu.component.bean.Student
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ===================================================
 * Created by MackWu on 2019/11/1 17:03
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class ProviderActivity : AppCompatActivity() {

    private val studentObserver = StudentObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // registerContentObserver
        contentResolver.registerContentObserver(StudentProvider.ERROR_URI, true, studentObserver)

        btn_test.setOnClickListener { contentResolver.notifyChange(StudentProvider.ERROR_URI, null) }
    }

    override fun onDestroy() {
        super.onDestroy()
        // unregisterContentObserver
        contentResolver.unregisterContentObserver(studentObserver)
    }

   inner class StudentObserver: ContentObserver(Handler()) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            Log.d("TAG", "onChange...")
        }

        override fun onChange(selfChange: Boolean, uri: Uri?) {
            super.onChange(selfChange, uri)
            Log.d("TAG", "onChange... uri: $uri")
            if (null == uri) return
            val cursor = contentResolver.query(uri, null, null, null, null)
            cursor?.run {
                val list = arrayListOf<Student>()
                if (moveToFirst()) {
                    do {
                        val id = getInt(getColumnIndex("id"))
                        val name = getString(getColumnIndex("name"))
                        val age = getInt(getColumnIndex("age"))
                        val sex = getString(getColumnIndex("sex"))
                        val student = Student(id, name, age, sex)
                        list.add(student)
                    } while (moveToNext())
                }
                close()
                for (student in list) {
                    Log.d("TAG", student.toString())
                }
            }
        }
    }

}