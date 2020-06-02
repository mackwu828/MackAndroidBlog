package com.mackwu.component.other.performance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import kotlinx.android.synthetic.main.activity_test.*

/**
 * ================================================
 * Created by MackWu on 2019/8/27 16:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 *
 * ANR是指触屏和按键事件在特定时间内无响应。
 *
 * <h2>ANR超时时间</h2>
 * 触屏和按键事件5s、广播10s、前台服务20s、后台服务200s。具体的超时时间的定义在framework下的ActivityManagerService中
 *  // How long we wait until we timeout on key dispatching.
 * static final int KEY_DISPATCHING_TIMEOUT = 5*1000;
 *
 * <h2>ANR产生的原因</h2>
 * 1.当前的事件没有机会得到处理（即UI线程正在处理前一个事件，没有及时的完成或者looper被某种原因阻塞住了）
 * 2.当前的事件正在处理，但没有及时完成
 *
 * <h2>如何避免ANR</h2>
 * UI线程尽量只做跟UI相关的工作。耗时的工作（比如数据库操作，I/O，连接网络或者别的有可能阻碍UI线程的操作）把它放入单独的线程处理
 *
 * <h2>如何定位ANR</h2>
 * a.使用adb命令导出跟踪文件。adb pull data/anr/traces.txt
 * b.通过studio的Device File Explorer的data/anr/路径下直接查看traces.txt文件
 *
 * <h2>测试</h2>
 * 点击按钮，在主线程睡眠，一段时间后系统弹出程序无响应
 * 08-27 16:44:48.681 3018-3018/com.mackwu.performance I/art: Note: end time exceeds epoch:
 * 008-27 16:45:09.167 3018-3025/com.mackwu.performance I/art: Thread[3,tid=3025,WaitingInMainSignalCatcherLoop,Thread*=0xb3825c00,peer=0x12c000a0,"Signal Catcher"]: reacting to signal 3
 * 008-27 16:45:09.319 3018-3025/com.mackwu.performance I/art: Wrote stack traces to '/data/anr/traces.txt'
 *
 * <h2>分析trace.txt</h2>
 * ----- pid 3018 at 2019-08-27 16:45:09 ----- // 发生时间
 * Cmd line: com.mackwu.performance // 包名
 * ...
 * "main" prio=5 tid=1 Sleeping // 主线程。睡眠
 * | group="main" sCount=1 dsCount=0 obj=0x737cd000 self=0xb3825800
 * | sysTid=3018 nice=0 cgrp=default sched=0/0 handle=0xb771eea0
 * | state=S schedstat=( 173705251 30762606 253 ) utm=14 stm=2 core=1 HZ=100
 * | stack=0xbf1f6000-0xbf1f8000 stackSize=8MB
 * | held mutexes=
 * at java.lang.Thread.sleep!(Native method)
 * - sleeping on <0x23057dba> (a java.lang.Object)
 * at java.lang.Thread.sleep(Thread.java:1031)
 * - locked <0x23057dba> (a java.lang.Object)
 * at java.lang.Thread.sleep(Thread.java:985)
 * at com.mackwu.performance.anr.AnrActivity$onCreate$1.onClick(AnrActivity.kt:40) // 可以看到自己的包名
 */
class AnrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // 模拟ANR
        btn_test.setOnClickListener { Thread.sleep(Long.MAX_VALUE) }
    }
}