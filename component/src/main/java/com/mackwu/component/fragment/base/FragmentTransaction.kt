package com.mackwu.component.fragment.base

import androidx.fragment.app.Fragment

/**
 * ===================================================
 * Created by MackWu on 2020/3/30 10:26
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */
interface FragmentTransaction {

    /**
     * replace Fragment
     */
    fun replaceFragment(fragment: Fragment)

    /**
     * delay replace fragment
     */
    fun delayReplaceFragment(fragment: Fragment)

    /**
     * show Fragment
     */
     fun showFragment(fragment: Fragment)

}