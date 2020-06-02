package com.mackwu.component.other.adapt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import com.mackwu.component.R

/**
 * ===================================================
 * Created by MackWu on 2019/9/23 11:59
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 *
 * 状态栏
 *
 * <h2>隐藏状态栏</h2>
 * 方法1：代码添加window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
 * 方法2：style.xml中添加<item name="android:windowFullscreen">true</item>
 *
 * <h2>隐藏标题栏</h2>
 * 方法1：代码添加requestWindowFeature(Window.FEATURE_NO_TITLE)
 * 方法2：style.xml中修改<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
 *
 * <h2>沉浸式状态栏</h2>
 */
class StatusBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // 隐藏状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }
}