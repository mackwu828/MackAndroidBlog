package com.mackwu.view

import com.mackwu.view.opensource.FlycoTabActivity
import com.mackwu.view.util.SimpleBaseFragment
import com.mackwu.view.util.startActivityCls
import kotlinx.android.synthetic.main.fragment_open_source.*

/**
 * ===================================================
 * Created by MackWu on 2020/3/30 10:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class OpenSourceFragment : SimpleBaseFragment() {

    override val layoutId: Int = R.layout.fragment_open_source

    override fun initView() {
        btn_flycotab.setOnClickListener { startActivityCls(FlycoTabActivity::class.java) }
    }

}