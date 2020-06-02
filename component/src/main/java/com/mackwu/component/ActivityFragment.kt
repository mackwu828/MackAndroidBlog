package com.mackwu.component

import android.util.Log
import com.mackwu.component.activity.TargetActivity
import com.mackwu.component.fragment.base.SimpleBaseFragment
import com.mackwu.component.util.*
import com.mackwu.component.util.date.PersianCalendarAlgorithm
import com.mackwu.component.util.date.getJalaliDate
import kotlinx.android.synthetic.main.fragment_activity.*

/**
 * ===================================================
 * Created by MackWu on 2020/4/17 10:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="">Follow me</a>
 * ===================================================
 */
class ActivityFragment : SimpleBaseFragment() {

    override val layoutId: Int = R.layout.fragment_activity

    override fun initView() {

        btn_test.setOnClickListener { Log.d("TAG", PersianCalendarAlgorithm.getInstance(activity).jalaliDate) }

        // 启动方式：基础
        btn_start.setOnClickListener { startActivityCls(TargetActivity::class.java) }
        btn_start_for_result.setOnClickListener { startActivityClsForResult(TargetActivity::class.java, 0x01) }
        btn_start_best.setOnClickListener { TargetActivity.start(activity!!, "xxx") }

        // 启动方式：启动另一个进程的Activity
        btn_start_package.setOnClickListener { startPackageActivity("com.mackwu.demo", "com.mackwu.demo.MainActivity") }
        btn_start_action.setOnClickListener { startActionActivity("com.mackwu.action.TARGET_ACTIVITY") }
        btn_start_launch.setOnClickListener { startLaunchActivity("com.mackwu.xxx") }
    }
}