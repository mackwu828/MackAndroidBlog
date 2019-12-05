package com.mackwu.component.util

import java.io.File

/**
 * ===================================================
 * Created by MackWu on 2019/11/27 11:36
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
private val file = File("mack.pcm")

fun main() {
    val file = File("mack.pcm")
    println(file.path)

//    // 获取项目路径。C:\Android\workspace\MackAndroidBlog
//    println(File("").canonicalPath)
//    // C:\Android\workspace\MackAndroidBlog
//    println(System.getProperty("user.dir"))
//
//    // 获取类的工程路径。C:\Android\workspace\MackAndroidBlog\component\build\tmp\kotlin-classes\debug\com\mackwu\component\other
//    println(File(MainActivity::class.java.getResource("")?.path))
//
//    // 获取类加载的根路径。C:\Android\android-sdk\platforms\android-28\data\res
//    println(File(MainActivity::class.java.getResource("/")?.path))
//    // C:\Android\android-sdk\platforms\android-28\data\res
//    println(File(MainActivity::class.java.classLoader?.getResource("")?.path))
//    // file:/C:/Android/android-sdk/platforms/android-28/data/res/
//    println(MainActivity::class.java.classLoader?.getResource(""))
}