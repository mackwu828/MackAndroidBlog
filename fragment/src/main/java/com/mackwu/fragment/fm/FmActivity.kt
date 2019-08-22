package com.mackwu.fragment.fm

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.mackwu.fragment.R
import kotlinx.android.synthetic.main.fm_activity.*

/**
 * ================================================
 * Created by MackWu on 2019/8/14 14:49
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * ================================================
 */
class FmActivity : AppCompatActivity() {

    private var homeFragment: Fragment? = null
    private var userFragment: Fragment? = null
    private var settingFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fm_activity)

        showFragment(0)
        tv_home.setOnClickListener { showFragment(0) }
        tv_user.setOnClickListener { showFragment(1) }
        tv_setting.setOnClickListener { showFragment(2) }
    }

    /**
     * show Fragment
     */
    private fun showFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.run {
            hideAllFragment(this)

            // 主页面
            when (position) {
                0 -> {
                    if (null == homeFragment) {
                        homeFragment = HomeFragment()
                        homeFragment?.run { add(R.id.fl_container, this, HomeFragment::class.java.simpleName) }
                    } else {
                        homeFragment?.run { show(this) }
                    }
                }
                1 -> {
                    if (null == userFragment) {
                        userFragment = UserFragment()
                        userFragment?.run { add(R.id.fl_container, this, UserFragment::class.java.simpleName) }
                    } else {
                        userFragment?.run { show(this) }
                    }
                }
                2 -> {
                    if (null == settingFragment) {
                        settingFragment = SettingFragment()
                        settingFragment?.run { add(R.id.fl_container, this, SettingFragment::class.java.simpleName) }
                    } else {
                        settingFragment?.run { show(this) }
                    }
                }
            }
            transaction.commit()
        }
    }

    /**
     * hide all fragment
     */
    private fun hideAllFragment(transaction: FragmentTransaction) {
        transaction.run {
            homeFragment?.run { hide(this) }
            userFragment?.run { hide(this) }
            settingFragment?.run { hide(this) }
        }
    }

}