package com.mackwu.component.fragment

import com.mackwu.component.R
import com.mackwu.component.fragment.base.TransactionBaseFragment

/**
 * ===================================================
 * Created by MackWu on 2020/3/30 10:23
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class HomeFragment : TransactionBaseFragment() {
    override val layoutId: Int = R.layout.fragment_home
    override fun initView() {
    }
}

class PromoteFragment : TransactionBaseFragment() {
    override val layoutId: Int = R.layout.fragment_promote
    override fun initView() {
    }
}

class UserFragment : TransactionBaseFragment() {
    override val layoutId: Int = R.layout.fragment_user
    override fun initView() {
    }
}