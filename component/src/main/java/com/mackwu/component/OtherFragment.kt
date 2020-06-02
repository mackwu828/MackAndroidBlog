package com.mackwu.component

import com.mackwu.component.other.NoStatusBarActivity
import com.mackwu.component.other.NonFullActivity
import com.mackwu.component.other.floatwindow.FloatWindowActivity
import com.mackwu.component.fragment.base.SimpleBaseFragment
import com.mackwu.component.other.usb.UsbActivity
import com.mackwu.component.util.startActivityCls
import kotlinx.android.synthetic.main.fragment_other.*

/**
 * ===================================================
 * Created by MackWu on 2020/4/17 10:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class OtherFragment : SimpleBaseFragment() {

    override val layoutId: Int = R.layout.fragment_other

    override fun initView() {

        // 样式
        btn_non_full.setOnClickListener { startActivityCls(NonFullActivity::class.java) }
        btn_no_status_bar.setOnClickListener { startActivityCls(NoStatusBarActivity::class.java) }

        //
        btn_float_window.setOnClickListener { startActivityCls(FloatWindowActivity::class.java) }
        btn_usb.setOnClickListener { startActivityCls(UsbActivity::class.java) }
    }
}