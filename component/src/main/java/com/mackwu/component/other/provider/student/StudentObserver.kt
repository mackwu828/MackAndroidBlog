package com.mackwu.component.other.provider.student

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.util.Log

/**
 * ===================================================
 * Created by MackWu on 2019/12/30 16:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class StudentObserver(val context: Context, handler: Handler) : ContentObserver(handler) {

    override fun onChange(selfChange: Boolean) {
        super.onChange(selfChange)
        Log.d(TAG, "onChange...")
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        super.onChange(selfChange, uri)
        Log.d(TAG, "onChange... uri: $uri")
        if (null == uri) return
        val cursor = context.contentResolver.query(uri, null, null, null, null)
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
                Log.d(TAG, student.toString())
            }
        }
    }

    companion object {
        private const val TAG = "StudentObserver"
    }
}