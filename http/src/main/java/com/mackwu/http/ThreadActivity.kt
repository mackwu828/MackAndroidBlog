package com.mackwu.http

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.http.R

/**
 * ===================================================
 * Created by MackWu on 2019/10/14 17:12
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 *
 * <h2>进程</h2>
 * 进程就是应用程序的启动实例。比如我们运行一个游戏，打开一个软件，就是开启了一个进程
 * 进程拥有代码和打开的文件资源、数据资源、独立的内存空间。
 *
 * <h2>线程</h2>
 * 线程从属于进程，是程序的实际执行者。一个进程至少包含一个主线程，也可以有更多的子线程。
 * 线程拥有自己的栈空间。
 *
 */
class ThreadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}