package com.mackwu.view

import com.mackwu.view.basic.recycler.RecyclerActivity
import com.mackwu.view.util.SimpleBaseFragment
import com.mackwu.view.util.startActivityCls
import kotlinx.android.synthetic.main.fragment_basic.*

/**
 * ===================================================
 * Created by MackWu on 2020/3/30 10:10
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class BasicFragment : SimpleBaseFragment() {

    override val layoutId: Int = R.layout.fragment_basic

    override fun initView() {
        btn_recycler_view.setOnClickListener { startActivityCls(RecyclerActivity::class.java) }
    }

}