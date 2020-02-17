package com.mackwu.component.service.bind

import android.os.Binder
import com.mackwu.service.start.BindService

/**
 * ===================================================
 * Created by MackWu on 2020/2/17 16:11
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 * 扩展Binder类。只能与当前进程的组件进行交互。
 *
 * 持有服务的实例，其他组件可以通过该实例调用服务的方法
 */
class MyBinder(val service: BindService) : Binder()