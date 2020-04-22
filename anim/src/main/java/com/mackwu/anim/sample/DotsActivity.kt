package com.mackwu.anim.sample

import com.mackwu.anim.R
import com.mackwu.anim.util.BaseActivity
import com.mackwu.anim.util.startDotsAnim
import kotlinx.android.synthetic.main.activity_dots.*

/**
 * ===================================================
 * Created by MackWu on 2020/4/14 14:01
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
class DotsActivity: BaseActivity() {

    override val layoutId: Int = R.layout.activity_dots

    override fun initView() {
        tv_loading.startDotsAnim("Installing")
    }

}