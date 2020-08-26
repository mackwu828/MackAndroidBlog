package com.mackwu.component.service.other

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import com.mackwu.service.start.BindService

/**
 * ===================================================
 * Created by MackWu on 2020/2/17 11:55
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class BindServiceConnection : ServiceConnection {

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