package com.mackwu.component

import com.mackwu.component.fragment.base.SimpleBaseFragment
import com.mackwu.component.jetpack.viewmodel.TestViewModelActivity
import com.mackwu.component.util.startActivityCls
import kotlinx.android.synthetic.main.fragment_jetpack.*

/**
 * ===================================================
 * Created by MackWu on 2020/4/17 10:31
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class JetpackFragment : SimpleBaseFragment() {

    override val layoutId: Int = R.layout.fragment_jetpack

    override fun initView() {
        btn_view_model.setOnClickListener { startActivityCls(TestViewModelActivity::class.java) }
    }
}