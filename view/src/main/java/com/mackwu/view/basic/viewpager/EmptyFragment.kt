package com.mackwu.view.basic.viewpager

import com.mackwu.view.R
import com.mackwu.view.util.SimpleBaseFragment

/**
 * ===================================================
 * Created by MackWu on 2020/3/30 10:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class HomeFragment : SimpleBaseFragment() {
    override val layoutId: Int = R.layout.fragment_home
    override fun initView() {
    }
}

class PromoteFragment : SimpleBaseFragment() {
    override val layoutId: Int = R.layout.fragment_promote
    override fun initView() {
    }
}

class UserFragment : SimpleBaseFragment() {
    override val layoutId: Int = R.layout.fragment_user
    override fun initView() {
    }
}