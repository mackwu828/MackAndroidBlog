package com.mackwu.component.other

import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import com.mackwu.component.BuildConfig
import com.mackwu.component.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

/**
 * ===================================================
 * Created by MackWu on 2019/11/19 14:47
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class PathActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener {

        }
    }
}

fun main() {
    // 获取项目路径。C:\Android\workspace\MackAndroidBlog
    println(File("").canonicalPath)
    // C:\Android\workspace\MackAndroidBlog
    println(System.getProperty("user.dir"))

    // 获取类的工程路径。C:\Android\workspace\MackAndroidBlog\component\build\tmp\kotlin-classes\debug\com\mackwu\component\other
    println(File(PathActivity::class.java.getResource("")?.path))

    // 获取类加载的根路径。C:\Android\android-sdk\platforms\android-28\data\res
    println(File(PathActivity::class.java.getResource("/")?.path))
    // C:\Android\android-sdk\platforms\android-28\data\res
    println(File(PathActivity::class.java.classLoader?.getResource("")?.path))
    // file:/C:/Android/android-sdk/platforms/android-28/data/res/
    println(PathActivity::class.java.classLoader?.getResource(""))

}