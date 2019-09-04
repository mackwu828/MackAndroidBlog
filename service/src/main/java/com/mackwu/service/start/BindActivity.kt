package com.mackwu.service.start

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.mackwu.service.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ================================================
 * Created by MackWu on 2019/9/4 10:48
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class BindActivity : AppCompatActivity() {

    private val bindConnection = BindConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_test.text = "bindService"
        btn_test.setOnClickListener { bindService(Intent(this, BindService::class.java), bindConnection, Service.BIND_AUTO_CREATE) }
    }

    override fun onDestroy() {
        super.onDestroy()
        // has leaked ServiceConnection com.mackwu.service.start.StartActivity$BindConnection@1a55a578 that was originally bound here
        // 需要在onDestroy解绑服务
        unbindService(bindConnection)
    }

    inner class BindConnection : ServiceConnection {

        private var bindService: BindService? = null

        /**
         * 服务意外中断时回调，如服务奔溃或被系统终止。客户端解绑服务不会调用该方法
         */
        override fun onServiceDisconnected(name: ComponentName?) {
            bindService = null
        }

        /**
         * 绑定服务时回调
         * @param service Service中onBind返回的IBinder对象
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BindService.LocalBinder
            bindService = binder.service
            // java.lang.Exception: denglibo Toast callstack! strTip=has bound Service
            bindService?.test()
        }
    }
}