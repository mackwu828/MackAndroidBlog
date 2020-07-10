package com.mackwu.component.util.kt

import android.content.Context
import android.os.Environment
import android.util.Log

/**
 * ===================================================
 * Created by MackWu on 2019/11/27 11:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
/**
 * 判断sd卡是否被挂载。
 */
val isSDCardMounted: Boolean
    get() = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

/**
 * 外置存储：/storage、/sdcard
 *
 * Environment.getExternalStorageState(): mounted。用来判断sd卡是否被挂载。
 * Environment.getExternalStorageDirectory(): /storage/emulated/0
 * Environment.getExternalStorageDirectory(): /storage/emulated/0/Download
 */
fun externalPath() {
    Log.d("TAG", """
         
        ${Environment.getExternalStorageState()}
        ${Environment.getExternalStorageDirectory().absolutePath}
        ${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath}
    """.trimIndent())
}

/**
 * 内置存储路径：data/
 *
 * filesDir.absolutePath：
 * cacheDir.absolutePath：
 */
fun Context.buildInPath(){
    Log.d("TAG", """
         
        ${filesDir.absolutePath}
        ${cacheDir.absolutePath}
    """.trimIndent())
}