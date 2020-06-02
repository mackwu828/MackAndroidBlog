package com.mackwu.component.service

import android.app.Service
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mackwu.component.R
import com.mackwu.component.util.startActionService
import com.mackwu.component.util.startServiceCls
import com.mackwu.component.util.stopServiceCls
import com.mackwu.service.start.BindService
import kotlinx.android.synthetic.main.activity_service.*

/**
 * ================================================
 * Created by MackWu on 2019/8/23 15:14
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ================================================
 */
class TestServiceActivity : AppCompatActivity() {

    private val serviceConnection = BindServiceConnection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        // 启动服务
        btn_start_service.setOnClickListener { startServiceCls(TestService::class.java) }
        // 停止服务
        btn_stop_service.setOnClickListener { stopServiceCls(TestService::class.java) }

        // 绑定服务
        btn_bind_service.setOnClickListener { bindService(Intent(this, BindService::class.java), serviceConnection, Service.BIND_AUTO_CREATE) }
        // 解绑服务
        btn_unbind_service.setOnClickListener { unbindService(serviceConnection) }

        // 通过action启动服务
        btn_start_action.setOnClickListener { startActionService("com.mackwu.demo", "com.mackwu.action.TARGET_SERVICE") }
    }

}