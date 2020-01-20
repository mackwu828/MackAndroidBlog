package com.mackwu.view.leanback

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.view.R

/**
 * ================================================
 * Created by MackWu on 2019/9/12 16:41
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * Launcher
 * Launcher就是Android的桌面应用程序。主要功能有：
 * 1.显示并启动已安装的应用程序
 * 2.桌面壁纸
 * 3.系统Widget
 * 4,下载安装
 *
 * <h2>配置为Launcher</h2>
 * 在AndroidManifest中给activity配置，应用启动时系统会识别为桌面应用程序
 * <category android:name="android.intent.category.HOME" />
 * <category android:name="android.intent.category.DEFAULT" />
 *
 */
class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}