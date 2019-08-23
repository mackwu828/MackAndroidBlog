package com.mackwu.activity.launchmode

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mackwu.activity.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/8/22 17:06
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * Activity有四种启动模式：standard、singleTop、singleTask、singleInstance。
 *
 * <h2>设置启动模式</h2>
 * 在AndroidManifest的activity标签中设置启动模式
 *
 * <h2>standard</h2>
 * Activity的默认启动方式。每次启动Activity都会新建一个Activity实例，然后添加到栈中。
 *
 * <h2>Activity栈</h2>
 * Activity是通过Activity栈管理的，当一个Activity启动时，系统会根据配置将Activity添加到栈中。
 * 如果用户点击返回或者finish结束了该Activity，那么该Activity会从栈中移除
 *
 * <h2>adb命令查看Activity栈中的情况</h2>
 * 打开控制台，输入
 * //        C:\Android\workspace\mackblog>adb shell
 * //        root@aosp:/ # dumpsys activity | grep -i run
 *
 * <h2>测试/h2>
 * 点击1次startActivity，输入adb命令，可以看到Activity栈中添加了1个Activity实例
 * //        Running activities (most recent first):
 * //        Run #0: ActivityRecord{71a16ba u0 com.mackwu.activity/.StandardActivity t39}
 *
 * 继续点击4次startActivity，输入adb命令，可以看到Activity栈中新添加了4个Activity实例
 * //        Running activities (most recent first):
 * //        Run #4: ActivityRecord{10bbac1f u0 com.mackwu.activity/.LaunchModeActivity t39}
 * //        Run #3: ActivityRecord{9569441 u0 com.mackwu.activity/.LaunchModeActivity t39}
 * //        Run #2: ActivityRecord{102c22d3 u0 com.mackwu.activity/.LaunchModeActivity t39}
 * //        Run #1: ActivityRecord{1be20855 u0 com.mackwu.activity/.LaunchModeActivity t39}
 * //        Run #0: ActivityRecord{71a16ba u0 com.mackwu.activity/.LaunchModeActivity t39}
 *
 */
class StandardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.text = "standard"
        btn_test.setOnClickListener { startActivity(Intent(this, StandardActivity::class.java)) }
    }
}